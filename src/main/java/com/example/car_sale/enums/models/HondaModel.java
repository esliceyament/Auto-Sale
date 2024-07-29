package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum HondaModel {
    ACCORD("Accord"),
    CIVIC("Civic"),
    CR_V("CR-V"),
    HR_V("HR-V"),
    PILOT("Pilot"),
    ODYSSEY("Odyssey"),
    RIDGELINE("Ridgeline"),
    FIT("Fit");

    private final String displayName;

    HondaModel(String displayName) {
        this.displayName = displayName;
    }
}
