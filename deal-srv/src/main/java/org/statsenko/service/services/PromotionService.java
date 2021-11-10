package org.statsenko.service.services;

import dto.PromotionDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.DealMapper;
import org.statsenko.mapper.PromotionMapper;
import org.statsenko.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PromotionService {

    private static final PromotionMapper REST_MAPPER = Mappers.getMapper(PromotionMapper.class);

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public PromotionDto getPromotionById(int id){

        PromotionDto promotion = REST_MAPPER.toDto(promotionRepository.findById(id).orElse(null));
        return promotion;
    }

    public List<PromotionDto> getAllPromotion(){
        List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(promotionRepository.findAll());
        return promotionDtoList;
    }

    public PromotionDto createPromotion(PromotionDto promotionDto){
        Promotion promotion = REST_MAPPER.toEntity(promotionDto);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    public PromotionDto editPromotion(PromotionDto promotionDto, int id){
        Promotion promotion = REST_MAPPER.toEntity(promotionDto);
        promotion.setId(id);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    public void deletePromotion(int id){
        promotionRepository.deleteById(id);
    }
}
