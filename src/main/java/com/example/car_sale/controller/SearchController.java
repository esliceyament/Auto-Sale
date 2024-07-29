package com.example.car_sale.controller;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.enums.Ban;
import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import com.example.car_sale.response.CarDetailResponse;
import com.example.car_sale.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/autos")
@RequiredArgsConstructor
public class SearchController {
    private final SiteService   siteService;

    @GetMapping("/{carId}")
    public String getCarDetails(@PathVariable Long carId, Model model) throws IOException {
        CarDetailResponse carDetailResponse = siteService.getCarDetails(carId);
        model.addAttribute("carDetailResponse", carDetailResponse);
        return "car-details"; // Return the view name (Thymeleaf template)
    }

    @GetMapping("/search")
    public String searchCars(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "startYear", required = false) Integer startYear,
            @RequestParam(value = "lastYear", required = false) Integer lastYear,
            @RequestParam(value = "ban", required = false) Ban ban,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "colour", required = false) Colour colour,
            @RequestParam(value = "minOdometer", required = false) Double minOdometer,
            @RequestParam(value = "maxOdometer", required = false) Double maxOdometer,
            @RequestParam(value = "minEngineType", required = false) Integer minEngineType,
            @RequestParam(value = "maxEngineType", required = false) Integer maxEngineType,
            @RequestParam(value = "fuelType", required = false) FuelType fuelType,
            @RequestParam(value = "city", required = false) City city,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            Model model1) {

        CarSearchCriteria criteria = new CarSearchCriteria();
        criteria.setMake(make);
        criteria.setModel(model);
        criteria.setStartYear(startYear);
        criteria.setLastYear(lastYear);
        criteria.setBan(ban);
        criteria.setMinPrice(minPrice);
        criteria.setMaxPrice(maxPrice);
        criteria.setColour(colour);
        criteria.setMinOdometer(minOdometer);
        criteria.setMaxOdometer(maxOdometer);
        criteria.setMinEngineType(minEngineType);
        criteria.setMaxEngineType(maxEngineType);
        criteria.setFuelType(fuelType);
        criteria.setCity(city);

        PageDto<CarDto> result = searchService.getCarsSearch(criteria, page, size);

        model1.addAttribute("cars", result);
        model1.addAttribute("page", page);
        model1.addAttribute("size", size);
        model1.addAttribute("totalPages", (result.getTotalElements() + result.getSize() - 1) / result.getSize());

        return "car-list";
    }
}
