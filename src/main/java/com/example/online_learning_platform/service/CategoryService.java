package com.example.online_learning_platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.Category;
import com.example.online_learning_platform.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public String saveCategory(Category category) {

        Boolean checkCategory = categoryRepository.existsByName(category.getName());
        if (checkCategory) {
            return "Category with this name already exists.";
        }

        categoryRepository.save(category);
        return "Category saved successfully.";
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return null;
        }
    }

}
