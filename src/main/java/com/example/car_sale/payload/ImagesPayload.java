package com.example.car_sale.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImagesPayload {

    private List<MultipartFile> files;
    private Long carId;

}

