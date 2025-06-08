package com.core.bms.dto;

import com.core.bms.model.Booking;
import lombok.Data;

@Data
public class BookingResponseDTO {
    private Booking booking;
    private ResponseStatus responseStatus;
}
