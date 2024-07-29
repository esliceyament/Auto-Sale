package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum MercedesBenzModel {
    A_CLASS("A-Class"),
    B_CLASS("B-Class"),
    C_CLASS("C-Class"),
    E_CLASS("E-Class"),
    S_CLASS("S-Class"),
    GLA("GLA"),
    GLB("GLB"),
    GLC("GLC"),
    GLE("GLE"),
    GLS("GLS"),
    EQC("EQC"),
    S_COUPE("S-Coupe");

    private final String displayName;

    MercedesBenzModel(String displayName) {
        this.displayName = displayName;
    }


}
