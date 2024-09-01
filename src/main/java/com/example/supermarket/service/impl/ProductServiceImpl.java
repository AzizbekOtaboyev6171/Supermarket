package com.example.supermarket.service.impl;

import com.example.supermarket.dto.product.ProductCreateDTO;
import com.example.supermarket.dto.product.ProductDTO;
import com.example.supermarket.dto.product.ProductUpdateDTO;
import com.example.supermarket.entity.Product;
import com.example.supermarket.exceptions.ResourceAlreadyExistsException;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.repository.CategoryRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.UnitRepository;
import com.example.supermarket.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product product = productRepository.findByBarcode(productCreateDTO.getBarcode())
                .orElseThrow(() -> new ResourceAlreadyExistsException("Product already exists with barcode: " + productCreateDTO.getBarcode()));

        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        return null;
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public List<ProductDTO> searchActiveProduct(int page, int size, String keyword) {
        return null;
    }
}