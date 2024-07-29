package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum BMWModel {
    SERIES_1("1 Series"),
    SERIES_2("2 Series"),
    SERIES_3("3 Series"),
    SERIES_4("4 Series"),
    SERIES_5("5 Series"),
    SERIES_6("6 Series"),
    SERIES_7("7 Series"),
    SERIES_8("8 Series"),
    X1("X1"),
    X2("X2"),
    X3("X3"),
    X4("X4"),
    X5("X5"),
    X6("X6"),
    X7("X7"),
    Z4("Z4"),
    I3("i3"),
    I4("i4"),
    IX("iX");

    private final String displayName;

    BMWModel(String displayName) {
        this.displayName = displayName;
    }
}
