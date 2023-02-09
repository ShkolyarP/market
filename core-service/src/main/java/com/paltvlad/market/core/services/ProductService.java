package com.paltvlad.market.core.services;


import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.api.ResourceNotFoundException;
import com.paltvlad.market.core.converters.ProductConverter;

import com.paltvlad.market.core.repositories.ProductRepository;
import com.paltvlad.market.core.repositories.specifications.ProductsSpecifications;
import com.paltvlad.market.core.soap.products.Product;
import com.paltvlad.market.core.validators.ProductValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ProductConverter productConverter;


    public Page<com.paltvlad.market.core.entities.Product> findAll(Double minPrice, Double maxPrice, String partTitle, Integer page) {

        Specification<com.paltvlad.market.core.entities.Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }


    @Transactional
    public void saveNewProduct(String title, double price) {
        com.paltvlad.market.core.entities.Product product = new com.paltvlad.market.core.entities.Product();
        product.setTitle(title);
        product.setPrice(price);

        if (product.getPrice() <= 0) {
            return;
        }
        productRepository.save(product);
    }

    public Optional<com.paltvlad.market.core.entities.Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<com.paltvlad.market.core.entities.Product> getALlProducts() {
        return productRepository.findAll();
    }

    public static final Function<com.paltvlad.market.core.entities.Product, Product> functionEntityToSoap = product -> {
        Product p = new Product();
        p.setId(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setCategoryTitle(product.getCategory().getTitle());
        return p;
    };

    public List<Product> getAllProductsSoap() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }


    public com.paltvlad.market.core.entities.Product createNewProduct(ProductDto productDto) {

        productValidator.validate(productDto);
        com.paltvlad.market.core.entities.Product product = productConverter.dtoToEntity(productDto);
        product.setId(null);


        return productRepository.save(product);
    }

    @Transactional
    public com.paltvlad.market.core.entities.Product update(ProductDto productDto) {
        com.paltvlad.market.core.entities.Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto.getId()));
        product.setPrice(product.getPrice());
        product.setTitle(product.getTitle());

        return product;
    }
}
