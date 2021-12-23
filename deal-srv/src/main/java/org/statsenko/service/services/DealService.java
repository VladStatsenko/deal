package org.statsenko.service.services;

import dto.DealDto;
import dto.MessageDto;
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
    public MessageDto<DealDto> getDealById(int id){
        MessageDto message = new MessageDto();
        if (dealRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Deal with your ID not found");
        }
        else {
            DealDto deal = REST_MAPPER.toDto(dealRepository.getById(id));
            message.setResponse(deal);
        }
        return message;
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
    public MessageDto<DealDto> editDeal(DealDto dealDto, int id){
        MessageDto message = new MessageDto();
        if (dealRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Deal with your ID not found");
        }
        else {
            Deal deal = REST_MAPPER.toEntity(dealDto);
            deal.setId(id);

            dealRepository.save(deal);
            message.setResponse(deal);
        }
        return message;
    }

    @Loggable
    @Transactional
    public MessageDto deleteDeal(int id){
        MessageDto message = new MessageDto();
        if (dealRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setDescription("Deal with your ID not found");
        }
        else {
            dealRepository.deleteById(id);
            message.setResponse("Deal deleted");
        }
        return message;
    }

    @Loggable
    public MessageDto<List<DealDto>> getAllDealWithPromotion(int id){
        MessageDto message = new MessageDto();
        if (dealRepository.findDealByPromotion(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setDescription("Promotion with your ID not found");
        }
        else {
            List<DealDto> dealDtoList = REST_MAPPER.toDtoList(dealRepository.findDealByPromotion(id));
            message.setResponse(dealDtoList);
        }
        return message;
    }
}
