package com.example.car_sale.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car_images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
