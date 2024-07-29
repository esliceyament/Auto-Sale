package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum LadaModel {
    NIVA("Niva"),
    GRANTA("Granta"),
    VESTA("Vesta"),
    X_RAY("X-Ray"),
    PRIORA("Priora"),
    VAZ_2107("VAZ 2107"),
    VAZ_2106("VAZ 2106"),
    VAZ_2105("VAZ 2105"),
    VAZ_2104("VAZ 2104"),
    VAZ_2103("VAZ 2103"),
    VAZ_2102("VAZ 2102"),
    VAZ_2101("VAZ 2101");

    private final String displayName;

    LadaModel(String displayName) {
        this.displayName = displayName;
    }
}
