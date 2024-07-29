package com.example.car_sale.dto;

import com.example.car_sale.enums.Ban;
import com.example.car_sale.response.ImagesResponse;
import lombok.Data;

import java.util.List;

@Data
public class CarDto {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private Ban ban;
    private String vin;
    private Double price;

    private CarFilterDto filters;
    private List<ImagesResponse> images;

    private Boolean status;
    private Boolean hidden;
    private Long orderNumber;

}
