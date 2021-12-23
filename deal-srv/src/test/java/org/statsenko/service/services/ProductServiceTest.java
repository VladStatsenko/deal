package org.statsenko.service.services;

import dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Currency;
import org.statsenko.entity.Product;
import org.statsenko.entity.ProductType;
import org.statsenko.mapper.ProductMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    private static final ProductMapper REST_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductType type1 = new ProductType(1,"DEBIT","debit cards",null,null,null);
    ProductType type2 = new ProductType(2,"CREDIT","credit cards",null,null,null);
    Currency currency = new Currency(1,"Rubles","RUB",null,null,null);

    Product product1 = new Product(1,"CREDIT CARD","15%",null,null,null,null,type2,currency);
    Product product2 = new Product(2,"DEBIT CARD","cashback",null,null,null,null,type1,currency);


    @Test
    void getProductById() {
        ProductDto productDto = REST_MAPPER.toDto(product1);

        assertEquals("CREDIT CARD", productDto.getName());
        assertEquals("15%",productDto.getDesc());
    }

    @Test
    void getAllProduct() {
        List<ProductDto> productDtoList = REST_MAPPER.toDtoList(List.of(product1,product2));

        assertEquals("CREDIT CARD", productDtoList.get(0).getName());
        assertEquals("DEBIT CARD", productDtoList.get(1).getName());
        assertEquals("CREDIT",productDtoList.get(0).getType());
        assertEquals("DEBIT",productDtoList.get(1).getType());

    }

    @Test
    void createProduct() {
        ProductDto productDto = new ProductDto("IPOTEKA","9%","2","1");
        Product product = REST_MAPPER.toEntity(productDto);

        assertEquals("IPOTEKA",product.getName());
        assertEquals(1,product.getCurrency().getId());
    }

    @Test
    void editProduct() {
        ProductDto productDto = new ProductDto("CREDIT CARD","9%","2","1");
        product1 = REST_MAPPER.toEntity(productDto);

        assertEquals("CREDIT CARD",product1.getName());
        assertEquals(1,product1.getCurrency().getId());
    }
}