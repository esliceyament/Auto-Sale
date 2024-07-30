package com.example.car_sale.controller;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.ImagesDto;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.enums.Ban;
import com.example.car_sale.enums.City;
import com.example.car_sale.enums.Colour;
import com.example.car_sale.enums.FuelType;
import com.example.car_sale.payload.CarFilterPayload;
import com.example.car_sale.payload.CarPayload;
import com.example.car_sale.payload.ImagesPayload;
import com.example.car_sale.service.CarService;
import com.example.car_sale.service.ImagesService;
import com.example.car_sale.service.SiteService;
import com.example.car_sale.validation.ValidVin;
import com.example.car_sale.validation.YearLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
@Validated
public class SiteController {
    private final SiteService siteService;
    private final CarService carService;
     private final ImagesService imagesService;

    @GetMapping("/search")
    public ResponseEntity<?> searchCars(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "startYear", required = false) Integer startYear,
            @RequestParam(value = "lastYear", required = false) Integer lastYear,
            @RequestParam(value = "ban", required = false) Ban ban,
            @RequestParam(value = "Minimum Price", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "colour", required = false) Colour colour,
            @RequestParam(value = "minOdometer", required = false) Double minOdometer,
            @RequestParam(value = "maxOdometer", required = false) Double maxOdometer,
            @RequestParam(value = "minEngineType", required = false) Integer minEngineType,
            @RequestParam(value = "maxEngineType", required = false) Integer maxEngineType,
            @RequestParam(value = "fuelType", required = false) FuelType fuelType,
            @RequestParam(value = "city", required = false) City city,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {

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

        PageDto<List<CarDto>> result = siteService.getCarsSearch(criteria, page, size);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add-new")
    public ResponseEntity<?> addCar(
            @RequestParam("make") String make,
            @RequestParam("model") String model,
            @RequestParam("year") @YearLimit Integer year,
            @RequestParam("ban") Ban ban,
            @RequestParam("vin") @ValidVin String vin,
            @RequestParam("price") Double price,
            @RequestParam(value = "colour") Colour colour,
            @RequestParam(value = "odometer") Double odometer,
            @RequestParam(value = "engineType") Integer engineType,
            @RequestParam(value = "fuelType") FuelType fuelType,
            @RequestParam(value = "city") City city,
            @RequestParam(value = "hidden", required = false) Boolean hidden
    ) {
        CarFilterPayload carFilterPayload = new CarFilterPayload();
        carFilterPayload.setColour(colour);
        carFilterPayload.setOdometer(odometer);
        carFilterPayload.setEngineType(engineType);
        carFilterPayload.setFuelType(fuelType);
        carFilterPayload.setCity(city);

        CarPayload carPayload = new CarPayload();
        carPayload.setMake(make);
        carPayload.setModel(model);
        carPayload.setYear(year);
        carPayload.setBan(ban);
        carPayload.setVin(vin);
        carPayload.setPrice(price);
        carPayload.setFilters(carFilterPayload);
        carPayload.setHidden(hidden);

        carService.addCar(carPayload);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam(value = "carId", required = false) Long carId) {
        if (files.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Files must not be empty");
        }

        ImagesPayload payload = new ImagesPayload();
        payload.setFiles(files);
        payload.setCarId(carId);

        List<ImagesDto> imageDtos = imagesService.uploadImages(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(imageDtos);
    }

}


