package com.core.bms.controller;

import com.core.bms.dto.BookingRequestDTO;
import com.core.bms.dto.BookingResponseDTO;
import com.core.bms.dto.ResponseStatus;
import com.core.bms.model.Booking;
import com.core.bms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public BookingResponseDTO bookTicket(BookingRequestDTO bookingRequestDTO){
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        try {
            Booking booking = bookingService.bookTicket(bookingRequestDTO.getUserId(), bookingRequestDTO.getShowId(), bookingRequestDTO.getShowSeatIds());
            bookingResponseDTO.setBooking(booking);
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookingResponseDTO;
    }


}
