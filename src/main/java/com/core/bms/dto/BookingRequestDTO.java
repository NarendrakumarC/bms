package com.core.bms.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
}
