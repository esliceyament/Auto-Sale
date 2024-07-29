package com.example.car_sale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {
    private int page;
    private int size;
    private long totalElements;
    private List<T> content;
}

