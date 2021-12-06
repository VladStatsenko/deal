package org.statsenko.service.services;

import dto.PromotionDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.PromotionMapper;
import org.statsenko.repository.PromotionRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PromotionService {

    private static final PromotionMapper REST_MAPPER = Mappers.getMapper(PromotionMapper.class);

    private final PromotionRepository promotionRepository;

    @Loggable
    public PromotionDto getPromotionById(int id){

        PromotionDto promotion = REST_MAPPER.toDto(promotionRepository.getById(id));
        return promotion;
    }

    @Loggable
    public List<PromotionDto> getAllPromotion(){
        List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(promotionRepository.findAll());
        return promotionDtoList;
    }

    @Loggable
    @Transactional
    public PromotionDto createPromotion(PromotionDto promotionDto){
        Promotion promotion = REST_MAPPER.toEntity(promotionDto);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    @Loggable
    @Transactional
    public PromotionDto editPromotion(PromotionDto promotionDto, int id){
        Promotion promotion = REST_MAPPER.toEntity(promotionDto);
        promotion.setId(id);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    @Loggable
    @Transactional
    public void deletePromotion(int id){
        promotionRepository.deleteById(id);
    }

    @Loggable
    public List<PromotionDto> getPromotionByProduct(int id){
        List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(promotionRepository.findPromotionByProduct(id));
        return promotionDtoList;
    }
}
