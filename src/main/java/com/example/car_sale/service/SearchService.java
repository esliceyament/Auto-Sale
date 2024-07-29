package com.example.car_sale.service;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.dto.CarSearchCriteria;
import com.example.car_sale.dto.PageDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.mapper.CarMapper;
import com.example.car_sale.repository.CarRepository;
import com.example.car_sale.repository.specifications.CarSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public PageDto<CarDto> getCarsSearch(CarSearchCriteria criteria, int page, int size) {
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

        List<CarDto> carDtoList = carPage.getContent().stream()
                .map(carMapper::toDto)
                .peek(carDto -> {
                    if (!carDto.getImages().isEmpty()) {
                        System.out.println("First image: " + carDto.getImages().get(0).getImage());
                    } else {
                        System.out.println("No images available for car ID: " + carDto.getId());
                    }
                })
                .toList();

        return new PageDto<>(carPage.getNumber() + 1, carPage.getSize(), carPage.getTotalElements(), carDtoList);
    }
}
