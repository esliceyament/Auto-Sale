package com.example.car_sale.enums;

import lombok.Getter;

@Getter
public enum Colour {
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    BLACK("Black"),
    WHITE("White"),
    SILVER("Silver"),
    GRAY("Gray"),
    YELLOW("Yellow"),
    BROWN("Brown"),
    ORANGE("Orange"),
    PURPLE("Purple"),
    BEIGE("Beige"),
    GOLD("Gold"),
    PINK("Pink"),
    TURQUOISE("Turquoise"),
    CYAN("Cyan");

    private final String displayName;

    Colour(String displayName) {
        this.displayName = displayName;
    }
}


