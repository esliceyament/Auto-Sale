package com.example.car_sale.mapper;

import com.example.car_sale.enums.models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarMakeModelMapping {
    private static final Map<String, Set<String>> makeToModelsMap = new HashMap<>();

    static {
        makeToModelsMap.put("Mercedes-Benz",
                Stream.of(MercedesBenzModel.values())
                        .map(MercedesBenzModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("BMW",
                Stream.of(BMWModel.values())
                        .map(BMWModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Rolls-Royce",
                Stream.of(RollsRoyceModel.values())
                        .map(RollsRoyceModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Alfa Romeo",
                Stream.of(AlfaRomeoModel.values())
                        .map(AlfaRomeoModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Audi",
                Stream.of(AudiModel.values())
                        .map(AudiModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Bentley",
                Stream.of(BentleyModel.values())
                        .map(BentleyModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Ferrari",
                Stream.of(FerrariModel.values())
                        .map(FerrariModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Ford",
                Stream.of(FordModel.values())
                        .map(FordModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Honda",
                Stream.of(HondaModel.values())
                        .map(HondaModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Toyota",
                Stream.of(ToyotaModel.values())
                        .map(ToyotaModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Jaguar",
                Stream.of(JaguarModel.values())
                        .map(JaguarModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Volkswagen",
                Stream.of(VolkswagenModel.values())
                        .map(VolkswagenModel::getDisplayName)
                        .collect(Collectors.toSet()));
        makeToModelsMap.put("Lada",
                Stream.of(LadaModel.values())
                        .map(LadaModel::getDisplayName)
                        .collect(Collectors.toSet()));
    }

    public static Set<String> getModelsByMake(String make) {
        return makeToModelsMap.getOrDefault(make, Set.of());
    }
}
