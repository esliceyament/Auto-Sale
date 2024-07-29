package com.example.car_sale.dto;

import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import lombok.Data;

@Data
public class CarFilterDto {

    private Long id;
    private Colour colour;
    private Double odometer;
    private Integer engineType;
    private FuelType fuelType;
    private City city;
    private Long carId;
    private Boolean status;
    private Long orderNumber;

}
