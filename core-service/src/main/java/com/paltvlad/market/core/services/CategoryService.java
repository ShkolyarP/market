package com.paltvlad.market.core.services;



import com.paltvlad.market.core.repositories.CategoryRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<com.paltvlad.market.core.entities.Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }



}
