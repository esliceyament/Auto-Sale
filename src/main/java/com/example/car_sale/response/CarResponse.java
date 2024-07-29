package com.example.car_sale.response;

import com.example.car_sale.enums.Ban;
import lombok.Data;

import java.util.List;

@Data
public class CarResponse {

    private String make;
    private String model;
    private Integer year;
    private Ban ban;
    private String vin;
    private Double price;

    private CarFilterResponse filters;
    private List<ImagesResponse> images;

}
