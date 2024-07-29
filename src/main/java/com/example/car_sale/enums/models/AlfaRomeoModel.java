package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum AlfaRomeoModel {
    GIULIA("Giulia"),
    STELVIO("Stelvio"),
    GIULIETTA("Giulietta"),
    MITO("MiTo");

    private final String displayName;

    AlfaRomeoModel(String displayName) {
        this.displayName = displayName;
    }
}

