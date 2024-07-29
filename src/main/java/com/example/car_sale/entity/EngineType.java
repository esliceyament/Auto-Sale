package com.example.car_sale.entity;

import java.util.ArrayList;
import java.util.List;

public class EngineType {

    public static List<Integer> generateValidEngineTypes() {
        List<Integer> engineTypes = new ArrayList<>();
        for (int i=0;i<=16000;i+=100) {
            engineTypes.add(i);
        }
        return engineTypes;
    }

}
