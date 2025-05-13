package com.ouaskanas.commerce.service.impl;

import com.ouaskanas.commerce.dto.request.CategoryDto;
import com.ouaskanas.commerce.model.Category;
import com.ouaskanas.commerce.repository.CategoryRepository;
import com.ouaskanas.commerce.service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String category) {
        return categoryRepository.findCategoryByCategoryName(category).orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Override
    public Category addCategory(CategoryDto category) {
        Category cat = Category.builder().categoryName(category.getCategory()).build();
        return categoryRepository.save(cat);
    }

    @Override
    public void DeleteCategory(String category) {
        categoryRepository.delete(getCategory(category));
    }
}
