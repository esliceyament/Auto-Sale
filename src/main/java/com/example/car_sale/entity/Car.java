package com.example.car_sale.entity;

import com.example.car_sale.enums.Ban;
import com.example.car_sale.validation.ValidVin;
import com.example.car_sale.validation.YearLimit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cars_table")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Ban ban;
    private String vin;
    @Max(value = 1000000)
    @Min(value = 100)
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="filters_id")
    private CarFilters filters;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Images> images;

    private boolean status;
    private boolean hidden;
    private Long orderNumber;

}
