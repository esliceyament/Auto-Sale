package com.example.car_sale.dto;

import com.example.car_sale.enums.Currency;
import lombok.Data;

@Data
public class SearchFilters {
    private Currency currency;
    private Double minOdometer;
    private Double maxOdometer;
}
