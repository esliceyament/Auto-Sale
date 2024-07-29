package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum JaguarModel {
    XE("XE"),
    XF("XF"),
    XJ("XJ"),
    E_PACE("E-PACE"),
    F_PACE("F-PACE"),
    I_PACE("I-PACE"),
    F_TYPE("F-TYPE");

    private final String displayName;

    JaguarModel(String displayName) {
        this.displayName = displayName;
    }
}
