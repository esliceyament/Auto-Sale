package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum FordModel {
    FIESTA("Fiesta"),
    FOCUS("Focus"),
    MONDEO("Mondeo"),
    MUSTANG("Mustang"),
    EDGE("Edge"),
    EXPLORER("Explorer"),
    ESCAPE("Escape"),
    F150("F-150"),
    RANGER("Ranger");

    private final String displayName;

    FordModel(String displayName) {
        this.displayName = displayName;
    }
}
