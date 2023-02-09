package com.paltvlad.market.carts.integrations;

import com.paltvlad.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {

        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market/api/v1/products/" + id, ProductDto.class));

    }
}
