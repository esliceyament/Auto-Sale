package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum BentleyModel {
    CONTINENTAL_GT("Continental GT"),
    FLYING_SPUR("Flying Spur"),
    BENTAYGA("Bentayga"),
    MULSANNE("Mulsanne");

    private final String displayName;

    BentleyModel(String displayName) {
        this.displayName = displayName;
    }
}
