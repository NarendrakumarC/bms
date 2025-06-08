package com.core.bms.model;

import com.core.bms.model.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Seat extends BaseModel{
    private String seatNo;
    private SeatType seatType;

    @ManyToOne
    private Screen screen;
}
