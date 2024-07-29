package com.example.car_sale.mapper;

import com.example.car_sale.dto.CarDto;
import com.example.car_sale.entity.Car;
import com.example.car_sale.payload.CarPayload;
import com.example.car_sale.response.CarResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = CarFilterMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarDto toDto(Car car);

    CarPayload toPayload(Car car);

    CarResponse toResponse(Car car);

    Car toEntity(CarDto carDto);

    Car toEntity(CarPayload carPayload);

    void updateCarFromDto(CarDto carDto, @MappingTarget Car car);

}



