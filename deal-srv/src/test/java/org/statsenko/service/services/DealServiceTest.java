package org.statsenko.service.services;

import dto.DealDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Deal;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.DealMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealServiceTest {

    private static final DealMapper REST_MAPPER = Mappers.getMapper(DealMapper.class);

    Deal deal1 = new Deal(1,null,1000,null,null,null,null);
    Deal deal2 = new Deal(2,null,2000,null,null,null,null);

    Promotion promotion1 = new Promotion(1,"sale","sale for all",null,null,null, List.of(deal1,deal2));


    @Test
    void getDealById() {
        DealDto dealDto = REST_MAPPER.toDto(deal1);

        assertEquals(1000,dealDto.getSum());

    }

    @Test
    void getAllDeal() {
        List<DealDto> dealDtoList = REST_MAPPER.toDtoList(List.of(deal1,deal2));

        assertEquals(1000,dealDtoList.get(0).getSum());
        assertEquals(2000,dealDtoList.get(1).getSum());
    }

    @Test
    void createDeal() {
        DealDto dealDto = new DealDto(null,3000,null,"1");
        Deal deal = REST_MAPPER.toEntity(dealDto);

        assertEquals(3000,deal.getSum());
        assertEquals(1,deal.getPromotion().getId());
    }

    @Test
    void editDeal() {
        DealDto dealDto = new DealDto(null,3000,null,"1");
        deal1 = REST_MAPPER.toEntity(dealDto);

        assertEquals(3000,deal1.getSum());
        assertEquals(1,deal1.getPromotion().getId());
    }

    @Test
    void getAllDealWithPromotion() {
        assertEquals(1000,promotion1.getDealList().get(0).getSum());
        assertEquals(2000,promotion1.getDealList().get(1).getSum());
    }
}