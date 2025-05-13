package com.ouaskanas.commerce.controller;

import com.ouaskanas.commerce.dto.request.CategoryDto;
import com.ouaskanas.commerce.model.Category;
import com.ouaskanas.commerce.service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getCategory(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto category){
        return  new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{category}")
    public ResponseEntity<String> deleteCategory(@PathVariable String category){
        categoryService.DeleteCategory(category);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

}
