package org.statsenko.mapper;

import dto.PromotionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.system.template.ViewMapper;

import java.util.List;

@Mapper
public interface PromotionMapper extends ViewMapper<Promotion, PromotionDto> {

    @Mapping(source = "product.name", target = "product")
    @Override
    PromotionDto toDto(Promotion entity);

    @Mapping(source = "product.name", target = "product")
    @Override
    List<PromotionDto> toDtoList(List<Promotion> entityList);

    @Mapping(source = "product", target = "product.id")
    @Override
    Promotion toEntity(PromotionDto dto);

    @Override
    List<Promotion> toEntityList(List<PromotionDto> dtoList);

}
