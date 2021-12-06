package org.statsenko.service.services;

import dto.DealDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Deal;
import org.statsenko.mapper.DealMapper;
import org.statsenko.repository.DealRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DealService {

    private static final DealMapper REST_MAPPER = Mappers.getMapper(DealMapper.class);

    private final DealRepository dealRepository;

    @Loggable
    public DealDto getDealById(int id){
        DealDto deal = REST_MAPPER.toDto(dealRepository.getById(id));
        return deal;
    }

    @Loggable
    public List<DealDto> getAllDeal(){
        List<DealDto> dealDtoList = REST_MAPPER.toDtoList(dealRepository.findAll());
        return dealDtoList;
    }

    @Loggable
    @Transactional
    public DealDto createDeal(DealDto dealDto){

        Deal deal = REST_MAPPER.toEntity(dealDto);

        dealRepository.save(deal);
        return dealDto;
    }

    @Loggable
    @Transactional
    public DealDto editDeal(DealDto dealDto, int id){

        Deal deal = REST_MAPPER.toEntity(dealDto);
        deal.setId(id);

        dealRepository.save(deal);
        return dealDto;
    }

    @Loggable
    @Transactional
    public void deleteDeal(int id){
        dealRepository.deleteById(id);
    }

    @Loggable
    public List<DealDto> getAllDealWithPromotion(int id){
        List<DealDto> dealDtoList = REST_MAPPER.toDtoList(dealRepository.findDealByPromotion(id));
        return dealDtoList;
    }
}
