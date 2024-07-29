package com.example.car_sale.enums;

import lombok.Getter;

@Getter
public enum FuelType {
    GAS("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    PLUGIN("Plug-In");

    private final String displayName;

    FuelType(String displayName) {
        this.displayName = displayName;
    }

}
