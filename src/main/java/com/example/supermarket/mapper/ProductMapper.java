package com.example.supermarket.mapper;

import com.example.supermarket.dto.product.ProductDTO;
import com.example.supermarket.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
}