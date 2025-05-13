package com.ouaskanas.commerce.service.services;

import com.ouaskanas.commerce.dto.request.ProductDto;
import com.ouaskanas.commerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product getProductById(Long id);
    public Product getProductByName(String name);
    public List<Product> getAllProducts();
    public List<Product> getAllProductsByCategory(String category);
    public Product addProduct(ProductDto productDto);
    public Product updateProduct(Long id, ProductDto productDto);
    public void deleteProduct(Long id);
}
