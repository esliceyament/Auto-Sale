package com.example.car_sale.dto;

import com.example.car_sale.enums.Ban;
import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import lombok.Data;


@Data
public class CarSearchCriteria {
    private String make;
    private String model;
    private Integer startYear;
    private Integer lastYear;
    private Ban ban;
    private Double minPrice;
    private Double maxPrice;
    private Colour colour;
    private Double minOdometer;
    private Double maxOdometer;
    private Integer minEngineType;
    private Integer maxEngineType;
    private FuelType fuelType;
    private City city;
}
