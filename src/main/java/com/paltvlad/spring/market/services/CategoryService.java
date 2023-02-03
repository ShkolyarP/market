package com.paltvlad.spring.market.services;



import com.paltvlad.spring.market.entities.Category;

import com.paltvlad.spring.market.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public static final Function<Category, com.paltvlad.spring.market.soap.categories.Category> functionEntityToSoap = ce -> {
        com.paltvlad.spring.market.soap.categories.Category c = new com.paltvlad.spring.market.soap.categories.Category();
        c.setTitle(ce.getTitle());
        ce.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(s -> c.getProducts().add(s));
        return c;
    };

    public com.paltvlad.spring.market.soap.categories.Category getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }


}
