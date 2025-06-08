package com.core.bms.service;

import com.core.bms.model.ShowSeat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceCalculationStrategy {

    public Double calculatePrice(List<ShowSeat> showSeats){
        Double amount = Double.valueOf(0);
        for (ShowSeat showSeat : showSeats){
            amount +=showSeat.getPrice();
        }
        return amount;
    }
}
