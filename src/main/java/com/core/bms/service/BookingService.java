package com.core.bms.service;

import com.core.bms.exception.SeatNoLongerAvaliableException;
import com.core.bms.exception.ShowNotFoundException;
import com.core.bms.exception.ShowSeatNotValidException;
import com.core.bms.exception.UserNotFoundException;
import com.core.bms.model.Booking;
import com.core.bms.model.Show;
import com.core.bms.model.ShowSeat;
import com.core.bms.model.User;
import com.core.bms.model.enums.BookingStatus;
import com.core.bms.model.enums.ShowSeatStatus;
import com.core.bms.repository.ShowRepository;
import com.core.bms.repository.ShowSeatRepository;
import com.core.bms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private PriceCalculationStrategy priceCalculationStrategy = new PriceCalculationStrategy();



    @Transactional
    public Booking bookTicket(Long userId, Long showId, List<Long> showSeatIds){
        // validate user and show
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User is not valid");
        }
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show selected is not valid");
        }
        User user = optionalUser.get();
        Show show = optionalShow.get();
        List<ShowSeat> showSeats =  showSeatRepository.findAllById(showSeatIds);
        if(showSeats.size()!=showSeatIds.size()){
            throw new ShowSeatNotValidException("All show seats are not valid");
        }
        for (ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatNoLongerAvaliableException("Some Seat Not Avaliable");
            }
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingNo("Booking_"+userId+"Show_"+showId);
        booking.setUser(user);

        for (ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);

        List<ShowSeat> lockedSeats = showSeatRepository.findAllShowSeatAndLock(showSeatIds);
        // Unlock the showseats if payment fails or time out
            for (ShowSeat seat : lockedSeats) {
                if (seat.getShowSeatStatus() == ShowSeatStatus.BLOCKED) {
                    seat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
                }
            }
            showSeatRepository.saveAll(lockedSeats);



        booking.setShowSeats(showSeats);
        booking.setNetAmount(priceCalculationStrategy.calculatePrice(showSeats));

        // Assume payment is done via gateway
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        for (ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
      showSeatRepository.saveAll(showSeats);

        

        return booking;

    }
}
