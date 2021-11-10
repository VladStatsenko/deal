package org.statsenko.mapper;

import dto.DealDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Deal;
import org.statsenko.mapper.system.template.ViewMapper;

import java.util.List;

@Mapper
public interface DealMapper extends ViewMapper<Deal, DealDto> {

    @Mapping(source = "product.name", target = "product")
    @Mapping(source = "promotion.name", target = "promotion")
    @Override
    DealDto toDto(Deal entity);

    @Mapping(source = "product.name", target = "product")
    @Mapping(source = "promotion.name", target = "promotion")
    @Override
    List<DealDto> toDtoList(List<Deal> entityList);

    @Mapping(source = "product", target = "product.id")
    @Mapping(source = "promotion", target = "promotion.id")
    @Override
    Deal toEntity(DealDto dto);

    @Override
    List<Deal> toEntityList(List<DealDto> dtoList);
}
