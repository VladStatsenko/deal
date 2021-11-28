package org.statsenko.service.services;

import dto.PromotionDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.PromotionMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionServiceTest {

    private static final PromotionMapper REST_MAPPER = Mappers.getMapper(PromotionMapper.class);

    Product product = new Product(1,"CREDIT CARD","15%",null,null,null,null,null,null);

    Promotion promotion1 = new Promotion(1,"SALE","15%",null,null,product,null);
    Promotion promotion2 = new Promotion(2,"CASHBACK","4%",null,null,product,null);

    Product product1 = new Product(1,"CREDIT CARD","15%",null,null,List.of(promotion1),null,null,null);

    @Test
    void getPromotionById() {
        PromotionDto promotionDto = REST_MAPPER.toDto(promotion1);

        assertEquals("SALE", promotionDto.getName());
        assertEquals("15%",promotionDto.getDesc());
    }

    @Test
    void getAllPromotion() {
        List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(List.of(promotion1,promotion2));

        assertEquals("SALE", promotionDtoList.get(0).getName());
        assertEquals("4%",promotionDtoList.get(1).getDesc());
    }

    @Test
    void createPromotion() {
        PromotionDto promotionDto = new PromotionDto("MONOPOLY","EVERYDAY PRIZE","1");
        Promotion promotion = REST_MAPPER.toEntity(promotionDto);

        assertEquals("MONOPOLY", promotion.getName());
        assertEquals(1,promotion.getProduct().getId());
    }

    @Test
    void editPromotion() {
        PromotionDto promotionDto = new PromotionDto("CASHBACK","EVERYDAY PRIZE","1");
        promotion1 = REST_MAPPER.toEntity(promotionDto);

        assertEquals("CASHBACK", promotion1.getName());
        assertEquals(1,promotion1.getProduct().getId());
    }

    @Test
    void getPromotionByProduct() {
        List<PromotionDto> promotionDtoList = REST_MAPPER.toDtoList(List.of(promotion1));

        assertEquals("CREDIT CARD", promotionDtoList.get(0).getProduct());
    }
}