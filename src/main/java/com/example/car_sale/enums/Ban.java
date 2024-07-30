package com.example.car_sale.enums;

import lombok.Getter;

@Getter
public enum Ban {
    SEDAN("Sedan"),
    HATCHBACK("Hatchback"),
    SUV("SUV"),
    COUPE("Coupe"),
    CONVERTIBLE("Convertible"),
    WAGON("Wagon"),
    PICKUP("Pickup"),
    VAN("Van"),
    ROADSTER("Roadster");

    private final String displayName;

    Ban(String displayName) {
        this.displayName = displayName;
    }
}
