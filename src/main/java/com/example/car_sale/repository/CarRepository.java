package com.example.car_sale.repository;

import com.example.car_sale.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    Optional<Car> findByVin(String vin);
    Page<Car> findAllByStatusTrueAndHiddenFalse(Pageable pageable);
    Optional<Car> findFirstByStatusTrueOrderByOrderNumberDesc();

}
