package com.example.car_sale.service;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.entity.EngineType;
import com.example.car_sale.entity.Images;
import com.example.car_sale.exception.AlreadyExistsException;
import com.example.car_sale.exception.NotFoundException;
import com.example.car_sale.mapper.CarMakeModelMapping;
import com.example.car_sale.mapper.CarMapper;
import com.example.car_sale.payload.CarPayload;
import com.example.car_sale.repository.CarRepository;
import com.example.car_sale.response.CarDetailResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public void addCar( CarPayload payload) {
        Optional<Car> car1 = carRepository.findByVin(payload.getVin());
        if (car1.isPresent()) {
            throw new AlreadyExistsException("Car with vin " + payload.getVin() + " exists!");
        }

        String make = payload.getMake();
        String model = payload.getModel();
        if (!isValidModelForMake(make, model)) {
            throw new IllegalStateException("Model " + model + " is not valid for make " + make);
        }

        Car car = carMapper.toEntity(payload);

        car.setOrderNumber(getOrderNumber() + 1);
        car.setStatus(true);

        if (payload.getHidden() == null) {
            car.setHidden(false);
        }

        if (payload.getFilters() != null) {
            car.getFilters().setOrderNumber(car.getOrderNumber());
            car.getFilters().setStatus(true);
            car.getFilters().setCar(car);
        }

        if (!EngineType.generateValidEngineTypes().contains(payload.getFilters().getEngineType())) {
            throw new IllegalStateException("Engine Type");
        }

        carRepository.save(car);
    }

    public CarDto updateCar(@NotNull CarDto carDto) {
        Car car = carRepository.findById(carDto.getId()).
                orElseThrow(() -> new NotFoundException("Car with " + carDto.getId() + " id wasn't found!"));

        carMapper.updateCarFromDto(carDto, car);
        carRepository.save(car);
        return carMapper.toDto(car);
    }


    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with " + id + " id wasn't found!"));

            return carMapper.toDto(car);
    }


//    public PageDto<List<CarDto>> getAllCars(int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Car> carPage = carRepository.findAllByStatusTrueAndHiddenFalse(pageable);
//        List<CarDto> carDtoList = carPage.getContent().stream().
//                map(carMapper::toDto).toList();
//        return new PageDto<>(size, carPage.getTotalElements(), List.of(carDtoList));
//    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Car with " + id + " id wasn't found!"));
        carRepository.delete(car);
    }

    public void move(Long first, Long second) {
        Car firstCar = carRepository.findById(first).
                orElseThrow(() -> new NotFoundException("Car with " + first + " id wasn't found!"));
        Car secondCar = carRepository.findById(second).
                orElseThrow(() -> new NotFoundException("Car with " + second + " id wasn't found!"));
        firstCar.setOrderNumber(secondCar.getOrderNumber());
        secondCar.setOrderNumber(firstCar.getOrderNumber());
        carRepository.save(firstCar);
        carRepository.save(secondCar);
    }
    private boolean isValidModelForMake(String make, String model) {
        Set<String> validModels = CarMakeModelMapping.getModelsByMake(make);
        return validModels.contains(model);
    }

    private Long getOrderNumber() {
        Optional<Car> car = carRepository.findFirstByStatusTrueOrderByOrderNumberDesc();
        if (car.isEmpty()) {
            return 0L;
        }
        else {
            return car.get().getOrderNumber();
        }
    }

    public CarDetailResponse getCarDetails(Long carId) throws IOException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car with ID " + carId + " not found"));

        CarDto carDto = carMapper.toDto(car); // Ensure this method correctly maps your Car entity to CarDto
        return new CarDetailResponse(carDto, Collections.emptyList()); // No images
    }



    public Long addCarWithID(CarPayload carPayload) {
        // Convert CarPayload to Car entity
        Car car = carMapper.toEntity(carPayload);

        // Save the car to the repository
        Car savedCar = carRepository.save(car);

        // Return the ID of the newly created car
        return savedCar.getId();
    }

}
