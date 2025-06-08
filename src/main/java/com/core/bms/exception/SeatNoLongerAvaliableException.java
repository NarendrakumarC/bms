package com.core.bms.exception;

public class SeatNoLongerAvaliableException extends RuntimeException {
    public SeatNoLongerAvaliableException(String seatNotAvaliable) {
        super(seatNotAvaliable);
    }
}
