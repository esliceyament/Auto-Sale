package com.example.car_sale.service;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.entity.Images;
import com.example.car_sale.exception.NotFoundException;
import com.example.car_sale.mapper.CarMapper;
import com.example.car_sale.repository.CarRepository;
import com.example.car_sale.repository.ImagesRepository;
import com.example.car_sale.repository.specifications.CarSpecifications;
import com.example.car_sale.response.CarDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final CarRepository carRepository;
    private final ImagesRepository imagesRepository;
    private final CarMapper carMapper;

    public PageDto<List<CarDto>> getCarsSearch(CarSearchCriteria criteria, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Specification<Car> specification = Specification.where(CarSpecifications.hasMake(criteria.getMake()))
                .and(CarSpecifications.hasModel(criteria.getModel()))
                .and(CarSpecifications.hasYearRange(criteria.getStartYear(), criteria.getLastYear()))
                .and(CarSpecifications.hasBan(criteria.getBan()))
                .and(CarSpecifications.hasPriceRange(criteria.getMinPrice(), criteria.getMaxPrice()))
                .and(CarSpecifications.hasColour(criteria.getColour()))
                .and(CarSpecifications.hasOdometerRange(criteria.getMinOdometer(), criteria.getMaxOdometer()))
                .and(CarSpecifications.hasEngineTypeRange(criteria.getMinEngineType(), criteria.getMaxEngineType()))
                .and(CarSpecifications.hasFuelType(criteria.getFuelType()))
                .and(CarSpecifications.hasCity(criteria.getCity()));

        Page<Car> carPage = carRepository.findAll(specification, pageable);

        System.out.println(carPage);
        List<CarDto> carDtoList = carPage.getContent().stream()
                .map(carMapper::toDto).toList();
        System.out.println(carDtoList);
        return new PageDto<>(page, size, carPage.getTotalElements(), List.of(carDtoList));
    }







    public CarDetailResponse getCarDetails(Long carId) throws IOException {
        // Fetch the car
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car with ID " + carId + " wasn't found!"));

        // Convert Car entity to CarDto
        CarDto carDto = carMapper.toDto(car);

        // Fetch and read images
        List<Images> images = car.getImages();
        List<String> imageContents = images.stream()
                .map(image -> {
                    try {
                        Path path = Paths.get(image.getFilePath());
                        byte[] imageBytes = Files.readAllBytes(path); // Read image as byte array
                        return Base64.getEncoder().encodeToString(imageBytes); // Encode to Base64
                    } catch (IOException e) {
                        throw new UncheckedIOException("Failed to read image file", e);
                    }
                })
                .collect(Collectors.toList());

        // Return a new CarDetailResponse with the car details and Base64 encoded image content
        return new CarDetailResponse(carDto, imageContents);
    }
}
