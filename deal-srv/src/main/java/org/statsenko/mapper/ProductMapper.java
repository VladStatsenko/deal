package org.statsenko.mapper;

import dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper extends ViewMapper<Product, ProductDto> {

    @Mapping(source = "productType.name", target = "type")
    @Mapping(source = "currency.code", target = "currency")
    @Override
    ProductDto toDto(Product entity);

    @Mapping(source = "productType.name", target = "type")
    @Mapping(source = "currency.code", target = "currency")
    @Override
    List<ProductDto> toDtoList(List<Product> entityList);

    @Mapping(source = "type", target = "productType.id")
    @Mapping(source = "currency", target = "currency.id")
    @Override
    Product toEntity(ProductDto dto);

    @Override
    List<Product> toEntityList(List<ProductDto> dtoList);
}
