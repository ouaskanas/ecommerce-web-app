package com.ouaskanas.commerce.service.impl;


import com.ouaskanas.commerce.dto.request.ProductDto;
import com.ouaskanas.commerce.model.Product;
import com.ouaskanas.commerce.repository.CategoryRepository;
import com.ouaskanas.commerce.repository.ProductRepository;
import com.ouaskanas.commerce.service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product getProductById(Long id) {
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found");
        }
        return productRepository.findById(id).get();
    }

    @Override
    public Product getProductByName(String name) {
        if(!productRepository.existsByProductName(name)){
            throw new RuntimeException("Product not found");
        }
        return productRepository.findProductsByProductName(name).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        var cate = categoryRepository.findCategoryByCategoryName(category).orElseThrow(()->new RuntimeException("Category not found"));
        return productRepository.findProductsByCategory(cate);
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        var product = Product.builder().productName(productDto.getProductName())
                .productDescription(productDto.getProductDescription())
                .productPrice(productDto.getProductPrice())
                .productImg(productDto.getProductImg())
                .productStock(productDto.getProductStock())
                .category(categoryRepository.findCategoryByCategoryName(productDto.getCategory()).orElseThrow(()->new RuntimeException("Category not found")))
                .build();
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id,ProductDto productDto) {
        var prd = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        prd.setProductName(productDto.getProductName());
        prd.setProductDescription(productDto.getProductDescription());
        prd.setProductPrice(productDto.getProductPrice());
        prd.setProductImg(productDto.getProductImg());
        prd.setProductStock(productDto.getProductStock());
        return productRepository.save(prd);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
