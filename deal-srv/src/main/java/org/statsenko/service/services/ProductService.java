package org.statsenko.service.services;

import dto.DealDto;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.statsenko.entity.*;
import org.statsenko.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository typeRepository;
    private final CurrencyRepository currencyRepository;

    public ProductDto getProductById(int id){
        ProductDto product = productRepository.findById(id)
                .map(ProductDto::new).get();
        return product;
    }

    public List<ProductDto> getAllProduct(){
        List<ProductDto> productDtoList = productRepository.findAll()
                .stream().map(ProductDto::new).collect(Collectors.toList());
        return productDtoList;
    }

    public ProductDto createProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDesc(productDto.getDesc());
        ProductType type = typeRepository.getById(productDto.getTypeId());
        product.setProductType(type);
        Currency currency = currencyRepository.getById(productDto.getCurrencyId());
        product.setCurrency(currency);
        productRepository.save(product);
        return productDto;
    }

    public ProductDto editProduct(ProductDto productDto, int id){
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productDto.getName());
        product.setDesc(productDto.getDesc());
        ProductType type = typeRepository.getById(productDto.getTypeId());
        product.setProductType(type);
        Currency currency = currencyRepository.getById(productDto.getCurrencyId());
        product.setCurrency(currency);
        productRepository.save(product);
        return productDto;
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
