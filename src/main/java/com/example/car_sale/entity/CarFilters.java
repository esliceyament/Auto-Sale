package com.example.car_sale.entity;

import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.Currency;
import com.example.car_sale.enums.FuelType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="car_filters")
public class CarFilters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double odometer;
    private Integer engineType;
    @Enumerated(EnumType.STRING)
    private Colour colour;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private boolean status;
    private Long orderNumber;
}
