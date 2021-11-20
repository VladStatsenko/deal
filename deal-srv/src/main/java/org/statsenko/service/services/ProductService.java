package org.statsenko.service.services;

import dto.DealDto;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.statsenko.entity.*;
import org.statsenko.mapper.DealMapper;
import org.statsenko.mapper.ProductMapper;
import org.statsenko.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final ProductMapper REST_MAPPER = Mappers.getMapper(ProductMapper.class);

    private final ProductRepository productRepository;

    public ProductDto getProductById(int id){
        ProductDto product = REST_MAPPER.toDto(productRepository.getById(id));
        return product;
    }

    public List<ProductDto> getAllProduct(){
        List<ProductDto> productDtoList = REST_MAPPER.toDtoList(productRepository.findAll());
        return productDtoList;
    }

    public ProductDto createProduct(ProductDto productDto){
        Product product = REST_MAPPER.toEntity(productDto);
        productRepository.save(product);
        return productDto;
    }

    public ProductDto editProduct(ProductDto productDto, int id){
        Product product = REST_MAPPER.toEntity(productDto);
        product.setId(id);
        productRepository.save(product);
        return productDto;
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
