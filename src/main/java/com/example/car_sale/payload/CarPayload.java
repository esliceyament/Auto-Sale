package com.example.car_sale.payload;

import com.example.car_sale.enums.Ban;
import com.example.car_sale.validation.ValidVin;
import com.example.car_sale.validation.YearLimit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CarPayload {

    @NotBlank(message = "Make should not be null")
    private String make;
    @NotBlank(message = "Model should not be null")
    private String model;
    @NotNull
    @YearLimit(message = "From 1900 to 2025")
    private Integer year;
    private Ban ban;
    @ValidVin(message = "Enter valid VIN")
    private String vin;
    @NotNull
    private Double price;

    private CarFilterPayload filters;

    private Boolean hidden;
}
