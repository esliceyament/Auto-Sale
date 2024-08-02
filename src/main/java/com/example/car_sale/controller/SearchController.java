package com.example.car_sale.controller;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.enums.Ban;
import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import com.example.car_sale.payload.*;
import com.example.car_sale.response.AuthenticationResponse;
import com.example.car_sale.response.CarDetailResponse;
import com.example.car_sale.service.*;
import com.example.car_sale.validation.ValidVin;
import com.example.car_sale.validation.YearLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/autos")
@RequiredArgsConstructor
public class SearchController {
    private final SiteService siteService;
    private final SearchService searchService;
    private final CarService carService;
    private final AuthenticationService authenticationService;
    private final ImagesService imagesService;

    @GetMapping("/{carId}")
    public String getCarDetails(@PathVariable Long carId, Model model) throws IOException {
        CarDetailResponse carDetailResponse = siteService.getCarDetails(carId);
        model.addAttribute("carDetailResponse", carDetailResponse);
        return "car-details";
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

        model1.addAttribute("cars", result.getContent());
        model1.addAttribute("fuelTypes", FuelType.values());
        model1.addAttribute("cities", City.values());
        model1.addAttribute("colours", Colour.values());
        model1.addAttribute("bans", Ban.values());
        model1.addAttribute("page", page);
        model1.addAttribute("size", size);
        model1.addAttribute("totalPages", (result.getTotalElements() + size - 1) / size);

        return "car-list";
    }

    @GetMapping("/new/auto")
    public String showAddCarForm(Model model) {
        model.addAttribute("carPayload", new CarPayload());
        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("cities", City.values());
        model.addAttribute("colours", Colour.values());
        model.addAttribute("bans", Ban.values());
        return "add-car";
    }

    @PostMapping(value = "/new/auto", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addCar(
            @RequestParam("make") String make,
            @RequestParam("model") String model,
            @RequestParam("year") @YearLimit Integer year,
            @RequestParam("ban") Ban ban,
            @RequestParam("vin") @ValidVin String vin,
            @RequestParam("price") Double price,
            @RequestParam(value = "colour", required = false) Colour colour,
            @RequestParam(value = "odometer", required = false) Double odometer,
            @RequestParam(value = "engineType", required = false) Integer engineType,
            @RequestParam(value = "fuelType", required = false) FuelType fuelType,
            @RequestParam(value = "city", required = false) City city,
            @RequestParam(value = "hidden", required = false) Boolean hidden,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            Model model1) {

        // Create a CarFilterPayload object
        CarFilterPayload carFilterPayload = new CarFilterPayload();
        carFilterPayload.setColour(colour);
        carFilterPayload.setOdometer(odometer);
        carFilterPayload.setEngineType(engineType);
        carFilterPayload.setFuelType(fuelType);
        carFilterPayload.setCity(city);

        // Create a CarPayload object and set its properties
        CarPayload carPayload = new CarPayload();
        carPayload.setMake(make);
        carPayload.setModel(model);
        carPayload.setYear(year);
        carPayload.setBan(ban);
        carPayload.setVin(vin);
        carPayload.setPrice(price);
        carPayload.setFilters(carFilterPayload);
        carPayload.setHidden(hidden);

        // Add the car using the CarService
        Long carId = carService.addCarWithID(carPayload);

        // Handle image uploads
        if (files != null && !files.isEmpty()) {
            ImagesPayload imagesPayload = new ImagesPayload();
            imagesPayload.setFiles(files);
            imagesPayload.setCarId(carId); // Associate images with the car ID

            imagesService.uploadImages(imagesPayload);
        }

        // Add a success message to the model
        model1.addAttribute("message", "Car added successfully!");

        // Redirect to the search page
        return "redirect:/autos/search";
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerPayload", new RegisterPayload());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterPayload payload, Model model) {
        authenticationService.register(payload);
        model.addAttribute("message", "Registration successful!");
        return "redirect:/autos/search";
    }

    @GetMapping("/authenticate")
    public String showAuthenticateForm(Model model) {
        model.addAttribute("authenticationPayload", new AuthenticationPayload());
        return "authenticate";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute AuthenticationPayload payload, Model model) {
        authenticationService.authenticate(payload);
        model.addAttribute("message", "Authentication successful!");
        return "redirect:/autos/search";
    }
}

