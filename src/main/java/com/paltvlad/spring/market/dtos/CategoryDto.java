package com.paltvlad.spring.market.dtos;


import com.paltvlad.spring.market.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    private Long id;

    private String title;

    private List<ProductDto> productsDto;



}
