package com.example.onck2.backend.services;

import com.example.onck2.backend.models.Category;
import com.example.onck2.backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
