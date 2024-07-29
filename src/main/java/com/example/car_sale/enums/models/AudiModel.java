package com.example.car_sale.enums.models;

import lombok.Getter;

@Getter
public enum AudiModel {
    A1("A1"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    Q2("Q2"),
    Q3("Q3"),
    Q4("Q4"),
    Q5("Q5"),
    Q7("Q7"),
    Q8("Q8"),
    TT("TT"),
    R8("R8");

    private final String displayName;

    AudiModel(String displayName) {
        this.displayName = displayName;
    }
}
