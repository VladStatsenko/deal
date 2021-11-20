package org.statsenko.service.services;

import dto.DealDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Deal;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.mapper.DealMapper;
import org.statsenko.repository.DealRepository;
import org.statsenko.repository.ProductRepository;
import org.statsenko.repository.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DealService {

    private static final DealMapper REST_MAPPER = Mappers.getMapper(DealMapper.class);

    private final DealRepository dealRepository;

    public DealDto getDealById(int id){
        DealDto deal = REST_MAPPER.toDto(dealRepository.getById(id));
        return deal;
    }

    public List<DealDto> getAllDeal(){
        List<DealDto> dealDtoList = REST_MAPPER.toDtoList(dealRepository.findAll());
        return dealDtoList;
    }

    public DealDto createDeal(DealDto dealDto){

        Deal deal = REST_MAPPER.toEntity(dealDto);

        dealRepository.save(deal);
        return dealDto;
    }

    public DealDto editDeal(DealDto dealDto, int id){

        Deal deal = REST_MAPPER.toEntity(dealDto);
        deal.setId(id);

        dealRepository.save(deal);
        return dealDto;
    }

    public void deleteDeal(int id){
        dealRepository.deleteById(id);
    }

    public List<DealDto> getAllDealWithPromotion(int id){
        List<DealDto> dealDtoList = REST_MAPPER.toDtoList(dealRepository.getAllDealWithPromotion(id));
        return dealDtoList;
    }
}
