package com.paltvlad.spring.market.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private Long id;

    private String title;

    private double price;



    private int amount = 1;
    private double sum;

    public ProductDto(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
