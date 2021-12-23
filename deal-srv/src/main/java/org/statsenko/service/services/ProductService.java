package org.statsenko.service.services;

import dto.MessageDto;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Product;
import org.statsenko.mapper.ProductMapper;
import org.statsenko.repository.ProductRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final ProductMapper REST_MAPPER = Mappers.getMapper(ProductMapper.class);

    private final ProductRepository productRepository;

    @Loggable
    public MessageDto<ProductDto> getProductById(int id){
        MessageDto message = new MessageDto();
        if (productRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Product with your ID not found");
        }
        else {
            ProductDto product = REST_MAPPER.toDto(productRepository.getById(id));
            message.setResponse(product);
        }
        return message;
    }

    @Loggable
    public List<ProductDto> getAllProduct(){
        List<ProductDto> productDtoList = REST_MAPPER.toDtoList(productRepository.findAll());
        return productDtoList;
    }

    @Loggable
    @Transactional
    public ProductDto createProduct(ProductDto productDto){
        Product product = REST_MAPPER.toEntity(productDto);
        productRepository.save(product);
        return productDto;
    }

    @Loggable
    @Transactional
    public MessageDto<ProductDto> editProduct(ProductDto productDto, int id){
        MessageDto message = new MessageDto();
        if (productRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Product with your ID not found");
        }
        else {
            Product product = REST_MAPPER.toEntity(productDto);
            product.setId(id);
            productRepository.save(product);
            message.setResponse(product);
        }
        return message;
    }

    @Loggable
    @Transactional
    public MessageDto deleteProduct(int id){
        MessageDto message = new MessageDto();
        if (productRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Product with your ID not found");
        }
        else {
            productRepository.deleteById(id);
            message.setResponse("Product deleted");
        }
        return message;
    }
}
