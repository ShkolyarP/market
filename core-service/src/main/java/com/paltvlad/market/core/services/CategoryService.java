package com.paltvlad.market.core.services;



import com.paltvlad.market.core.repositories.CategoryRepository;
import com.paltvlad.market.core.soap.categories.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<com.paltvlad.market.core.entities.Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public static final Function<com.paltvlad.market.core.entities.Category, Category> functionEntityToSoap = ce -> {
        Category c = new Category();
        c.setTitle(ce.getTitle());
        ce.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(s -> c.getProducts().add(s));
        return c;
    };

    public Category getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }


}
