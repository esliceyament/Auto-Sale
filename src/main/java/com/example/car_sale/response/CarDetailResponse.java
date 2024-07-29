package com.example.car_sale.response;

import com.example.car_sale.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarDetailResponse {
    private CarDto car;
    private List<String> images;
}
