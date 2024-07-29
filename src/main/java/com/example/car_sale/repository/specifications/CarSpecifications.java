package com.example.car_sale.repository.specifications;

import com.example.car_sale.entity.Car;
import com.example.car_sale.enums.Ban;
import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecifications {

    public static Specification<Car> hasMake(String make) {
        return (root, query, builder) ->
                make == null ? builder.conjunction() :
                        builder.like(builder.lower(root.get("make")), make.toLowerCase() + "%");
    }

    public static Specification<Car> hasModel(String model) {
        return (root, query, builder) ->
                model == null ? builder.conjunction() :
                        builder.like(builder.lower(root.get("model")), model.toLowerCase() + "%");
    }

    public static Specification<Car> hasYearRange(Integer startYear, Integer lastYear) {
        return (root, query, builder) -> {
            if (startYear == null && lastYear == null) {
                return builder.conjunction();
            }
            else if (startYear == null) {
                return builder.lessThanOrEqualTo(root.get("year"), lastYear);
            }
            else if (lastYear == null) {
                return builder.greaterThanOrEqualTo(root.get("year"), startYear);
            }
            else {
                return builder.between(root.get("year"), startYear, lastYear);
            }
        };
    }

    public static Specification<Car> hasBan(Ban ban) {
        return (root, query, builder) ->
                ban == null ? builder.conjunction() :
                        builder.equal(root.get("ban"), ban);
    }

    public static Specification<Car> hasPriceRange(Double minPrice, Double maxPrice) {
        return (root, query, builder) -> {
            if (minPrice == null && maxPrice == null) {
                return builder.conjunction();
            } else if (minPrice == null) {
                return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
            } else if (maxPrice == null) {
                return builder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return builder.between(root.get("price"), minPrice, maxPrice);
            }
        };
    }

    public static Specification<Car> hasColour(Colour colour) {
        return (root, query, builder) ->
                colour == null ? builder.conjunction() :
                        builder.equal(root.join("filters").get("colour"), colour);
    }

    public static Specification<Car> hasOdometerRange(Double minOdometer, Double maxOdometer) {
        return (root, query, builder) -> {
            if (minOdometer == null && maxOdometer == null) {
                return builder.conjunction();
            } else if (minOdometer == null) {
                return builder.lessThanOrEqualTo(root.join("filters").get("odometer"), maxOdometer);
            } else if (maxOdometer == null) {
                return builder.greaterThanOrEqualTo(root.join("filters").get("odometer"), minOdometer);
            } else {
                return builder.between(root.join("filters").get("odometer"), minOdometer, maxOdometer);
            }
        };
    }

    public static Specification<Car> hasEngineTypeRange(Integer minEngineType, Integer maxEngineType) {
        return (root, query, builder) -> {
            if (minEngineType == null && maxEngineType == null) {
                return builder.conjunction();
            } else if (minEngineType == null) {
                return builder.lessThanOrEqualTo(root.join("filters").get("engineType"), maxEngineType);
            } else if (maxEngineType == null) {
                return builder.greaterThanOrEqualTo(root.join("filters").get("engineType"), minEngineType);
            } else {
                return builder.between(root.join("filters").get("engineType"), minEngineType, maxEngineType);
            }
        };
    }

    public static Specification<Car> hasFuelType(FuelType fuelType) {
        return (root, query, builder) ->
                fuelType == null ? builder.conjunction() :
                        builder.equal(root.join("filters").get("fuelType"), fuelType);
    }

    public static Specification<Car> hasCity(City city) {
        return (root, query, builder) ->
                city == null ? builder.conjunction() :
                        builder.equal(root.join("filters").get("city"), city);
    }

}
