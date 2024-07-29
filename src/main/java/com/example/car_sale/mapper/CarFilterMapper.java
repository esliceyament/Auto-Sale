package com.example.car_sale.mapper;

import com.example.car_sale.dto.CarFilterDto;
import com.example.car_sale.entity.CarFilters;
import com.example.car_sale.payload.CarFilterPayload;
import com.example.car_sale.response.CarFilterResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarFilterMapper {

    @Mapping(source = "car.id", target = "carId")
    CarFilterDto toDto(CarFilters carFilters);

    CarFilterPayload toPayload(CarFilters carFilters);

    CarFilterResponse toResponse(CarFilters carFilters);

    @Mapping(source = "carId", target = "car.id")
    CarFilters toEntity(CarFilterDto carFilterDto);

    CarFilters toEntity(CarFilterPayload carFilterPayload);

    void updateCarFilterFromDto(CarFilterDto carFilterDto, @MappingTarget CarFilters carFilter);

}
