package com.example.car_sale.dto;

import lombok.Data;

@Data
public class ImagesDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long carId;

}

