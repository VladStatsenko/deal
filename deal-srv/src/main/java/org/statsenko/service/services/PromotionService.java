package org.statsenko.service.services;

import dto.PromotionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PromotionService {

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public PromotionDto getPromotionById(int id){
        PromotionDto promotion = promotionRepository.findById(id)
                .map(PromotionDto::new).get();
        return promotion;
    }

    public List<PromotionDto> getAllPromotion(){
        List<PromotionDto> promotionDtoList = promotionRepository.findAll()
                .stream().map(PromotionDto::new).collect(Collectors.toList());
        return promotionDtoList;
    }

    public PromotionDto createPromotion(PromotionDto promotionDto){
        Promotion promotion = new Promotion();
        promotion.setName(promotionDto.getName());
        promotion.setDesc(promotionDto.getDesc());
        Product product = productRepository.getById(promotionDto.getProductId());
        promotion.setProduct(product);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    public PromotionDto editPromotion(PromotionDto promotionDto, int id){
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        promotion.setName(promotionDto.getName());
        promotion.setDesc(promotionDto.getDesc());
        Product product = productRepository.getById(promotionDto.getProductId());
        promotion.setProduct(product);
        promotionRepository.save(promotion);
        return promotionDto;
    }

    public void deletePromotion(int id){
        promotionRepository.deleteById(id);
    }
}
