package com.core.bms.model;

import com.core.bms.model.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Booking extends BaseModel{
    private String bookingNo;
    private BookingStatus bookingStatus;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @OneToMany
    private List<Payment> payments;
    private Double netAmount;
}

/*
Booking  showseat
1       M
M       1 - cancellation case

bookingId = 105
show_id=3, seat_id =1
                    2
                    3 -- cancelled
bookingId = 106
show_id =3, seat_id =3 - cancelled seat is available for another bookingId

1000rupees
upi - 800
wallet - 200
1  M
1  1
BookingId  Payment - 1 : M

Booking   User
1        1
M        1


 */
