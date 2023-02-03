package com.paltvlad.spring.market.services;


import com.paltvlad.spring.market.converters.ProductConverter;
import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.entities.Product;
import com.paltvlad.spring.market.exeptions.ResourceNotFoundException;
import com.paltvlad.spring.market.repositories.ProductRepository;
import com.paltvlad.spring.market.repositories.specifications.ProductsSpecifications;
import com.paltvlad.spring.market.validators.ProductValidator;
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


    public Page<Product> findAll(Double minPrice, Double maxPrice, String partTitle, Integer page) {

        Specification<Product> spec = Specification.where(null);

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
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);

        if (product.getPrice() <= 0) {
            return;
        }
        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getALlProducts() {
        return productRepository.findAll();
    }

    public static final Function<Product, com.paltvlad.spring.market.soap.products.Product> functionEntityToSoap = product -> {
        com.paltvlad.spring.market.soap.products.Product p = new com.paltvlad.spring.market.soap.products.Product();
        p.setId(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setCategoryTitle(product.getCategory().getTitle());
        return p;
    };

    public List<com.paltvlad.spring.market.soap.products.Product> getALlProductsSoap() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }


    public Product createNewProduct(ProductDto productDto) {

        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product.setId(null);


        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto.getId()));
        product.setPrice(product.getPrice());
        product.setTitle(product.getTitle());

        return product;
    }
}
