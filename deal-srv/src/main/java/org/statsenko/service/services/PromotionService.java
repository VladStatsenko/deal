package org.statsenko.service.services;

import dto.MessageDto;
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
    public MessageDto<PromotionDto> getPromotionById(int id){
        MessageDto message = new MessageDto();
        if (promotionRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Promotion with your ID not found");
        }
        else {
            PromotionDto promotion = REST_MAPPER.toDto(promotionRepository.getById(id));
            message.setResponse(promotion);
        }
        return message;
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
    public MessageDto<PromotionDto> editPromotion(PromotionDto promotionDto, int id){
        MessageDto message = new MessageDto();
        if (promotionRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Promotion with your ID not found");
        }
        else {
            Promotion promotion = REST_MAPPER.toEntity(promotionDto);
            promotion.setId(id);
            promotionRepository.save(promotion);
            message.setResponse(promotion);
        }
        return message;
    }

    @Loggable
    @Transactional
    public MessageDto deletePromotion(int id){
        MessageDto message = new MessageDto();
        if (promotionRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Promotion with your ID not found");
        }
        else {
            promotionRepository.deleteById(id);
            message.setResponse("Promotion deleted");
        }
        return message;
    }

    @Loggable
    public MessageDto<List<PromotionDto>> getPromotionByProduct(int id){
        MessageDto message = new MessageDto();
        if (promotionRepository.findPromotionByProduct(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setDescription("Product with your ID not found");
        }
        else {
            List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(promotionRepository.findPromotionByProduct(id));
            message.setResponse(promotionDtoList);
        }
        return message;
    }
}
