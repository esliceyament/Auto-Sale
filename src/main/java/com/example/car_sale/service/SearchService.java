package com.example.car_sale.service;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.ImagesDto;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.mapper.CarMapper;
import com.example.car_sale.repository.CarRepository;
import com.example.car_sale.repository.specifications.CarSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class SearchService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public PageDto<CarDto> getCarsSearch(CarSearchCriteria criteria, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("orderNumber"));

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

        List<CarDto> carDtoList = carPage.getContent().stream()
                .map(car -> {
                    CarDto dto = carMapper.toDto(car);
                    if (!dto.getImages().isEmpty()) {
                        ImagesDto firstImage = dto.getImages().get(0);
                        try {
                            Path path = Paths.get(firstImage.getFilePath());
                            byte[] imageBytes = Files.readAllBytes(path); // Read image as byte array
                            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                            dto.setFirstImage(base64Image); // Assuming you add this field in CarDto
                        } catch (IOException e) {
                            throw new UncheckedIOException("Failed to read image file", e);
                        }
                    }
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageDto<>(carPage.getNumber() + 1, carPage.getSize(), carPage.getTotalElements(), carDtoList);
    }

}
