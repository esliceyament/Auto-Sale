package com.example.car_sale.response;

import lombok.Data;

@Data
public class ImagesResponse {

    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long carId;

}

