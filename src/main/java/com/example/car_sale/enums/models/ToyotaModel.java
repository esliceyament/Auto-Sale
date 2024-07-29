package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum ToyotaModel {
    CAMRY("Camry"),
    COROLLA("Corolla"),
    RAV4("RAV4"),
    HIGHLANDER("Highlander"),
    LAND_CRUISER("Land Cruiser"),
    TACOMA("Tacoma"),
    TUNDRA("Tundra"),
    PRIUS("Prius"),
    SUPRA("Supra");

    private final String displayName;

    ToyotaModel(String displayName) {
        this.displayName = displayName;
    }
}
