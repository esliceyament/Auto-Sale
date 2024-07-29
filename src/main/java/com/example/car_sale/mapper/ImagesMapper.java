package com.example.car_sale.mapper;

import com.example.car_sale.dto.ImagesDto;
import com.example.car_sale.entity.Images;
import com.example.car_sale.payload.ImagesPayload;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImagesMapper {
    @Mapping(source = "car.id", target = "carId")
    ImagesDto toDto(Images image);

    @Mapping(source = "car.id", target = "carId")
    ImagesPayload toPayload(Images image);

    @Mapping(source = "carId", target = "car.id")
    Images toEntity(ImagesDto imageDto);

    @Mapping(source = "carId", target = "car.id")
    Images toEntity(ImagesPayload imagePayload);

}
