package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum FerrariModel {
    GTB("488 GTB"),
    SPIDER("488 Spider"),
    TRIBUTO("F8 Tributo"),
    SPIDER_F8("F8 Spider"),
    SUPERFAST("812 Superfast"),
    PORTOFINO("Portofino"),
    ROMA("Roma"),
    SF90_STRADALE("SF90 Stradale");

    private final String displayName;

    FerrariModel(String displayName) {
        this.displayName = displayName;
    }

}
