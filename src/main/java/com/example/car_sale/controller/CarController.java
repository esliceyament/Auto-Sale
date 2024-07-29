package com.example.car_sale.controller;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.payload.CarPayload;
import com.example.car_sale.response.CarDetailResponse;
import com.example.car_sale.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody @Valid CarPayload carPayload) {
        carService.addCar(carPayload);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.updateCar(carDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

//    @GetMapping
//    public ResponseEntity<PageDto<List<CarDto>>> getAllCars(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "15") int size) {
//
//        PageDto<List<CarDto>> carPage = carService.getAllCars(page, size);
//
//        return new ResponseEntity<>(carPage, HttpStatus.OK);
//    }

    @PutMapping("/move")
    public ResponseEntity<?> move(@RequestParam Long first, @RequestParam Long second) {
        carService.move(first, second);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }




//    @GetMapping("/cars/{id}/details")
//    public String getCarDetails(@PathVariable Long id, Model model) throws IOException {
//        CarDetailResponse carDetail = carService.getCarDetails(id);
//        model.addAttribute("carDetail", carDetail);
//        return "car-details"; // Name of the Thymeleaf template file
//    }

    @GetMapping("/cars/{id}")
    public String getCarDetails(@PathVariable Long id, Model model) throws IOException {
        CarDetailResponse carDetail = carService.getCarDetails(id);
        System.out.println(carDetail);
        model.addAttribute("carDetail", carDetail);
        return "car-details"; // Refers to car-details.html
    }
}
