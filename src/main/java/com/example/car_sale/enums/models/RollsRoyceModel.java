package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum RollsRoyceModel {
    PHANTOM("Phantom"),
    GHOST("Ghost"),
    WRAITH("Wraith"),
    DAWN("Dawn"),
    CULLINAN("Cullinan"),
    SWEPTAIL("Sweptail");

    private final String displayName;

    RollsRoyceModel(String displayName) {
        this.displayName = displayName;
    }
}
