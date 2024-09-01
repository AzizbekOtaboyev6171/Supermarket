package com.example.supermarket.service;

import com.example.supermarket.dto.product.ProductCreateDTO;
import com.example.supermarket.dto.product.ProductDTO;
import com.example.supermarket.dto.product.ProductUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct(ProductCreateDTO productCreateDTO);
    ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO);
    Optional<ProductDTO> findProductById(Long id);
    void deleteProductById(Long id);
    List<ProductDTO> searchActiveProduct(int page, int size, String keyword);
}