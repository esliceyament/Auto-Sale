package com.example.car_sale.payload;

import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import lombok.Data;

@Data
public class CarFilterPayload {

    private Colour colour;
    private Double odometer;
    private Integer engineType;
    private FuelType fuelType;
    private City city;
}
