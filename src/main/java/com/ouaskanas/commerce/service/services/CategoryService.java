package com.ouaskanas.commerce.service.services;

import com.ouaskanas.commerce.dto.request.CategoryDto;
import com.ouaskanas.commerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<Category> getCategories();
    public  Category getCategory(String category);
    public Category addCategory(CategoryDto category);
    public void DeleteCategory(String category);
}
