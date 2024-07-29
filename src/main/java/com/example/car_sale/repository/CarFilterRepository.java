package com.example.car_sale.repository;

import com.example.car_sale.entity.CarFilters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarFilterRepository extends JpaRepository<CarFilters, Long> {
}
