package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum VolkswagenModel {
    GOLF("Golf"),
    JETTA("Jetta"),
    PASSAT("Passat"),
    ARTEON("Arteon"),
    TIGUAN("Tiguan"),
    ATLAS("Atlas"),
    ID_4("ID.4"),
    ID_3("ID.3");

    private final String displayName;

    VolkswagenModel(String displayName) {
        this.displayName = displayName;
    }
}
