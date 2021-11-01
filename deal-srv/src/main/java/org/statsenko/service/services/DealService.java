package org.statsenko.service.services;

import dto.DealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Deal;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.repository.DealRepository;
import org.statsenko.repository.ProductRepository;
import org.statsenko.repository.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DealService {

    private final DealRepository dealRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public DealDto getDealById(int id){
        DealDto deal = dealRepository.findById(id)
                .map(DealDto::new).get();
        return deal;
    }

    public List<DealDto> getAllDeal(){
        List<DealDto> dealDtoList = dealRepository.findAll().stream()
                .map(DealDto::new).collect(Collectors.toList());
        return dealDtoList;
    }

    public DealDto createDeal(DealDto dealDto){
        Deal deal = new Deal();
        deal.setDealDate(dealDto.getDealDate());
        deal.setSum(dealDto.getSum());
        Product product = productRepository.getById(dealDto.getProductId());
        deal.setProduct(product);
        Promotion promotion = promotionRepository.getById(dealDto.getPromotionId());
        deal.setPromotion(promotion);

        dealRepository.save(deal);
        return dealDto;
    }

    public DealDto editDeal(DealDto dealDto, int id){
        Deal deal = dealRepository.findById(id).orElse(null);
        deal.setDealDate(dealDto.getDealDate());
        deal.setSum(dealDto.getSum());
        Product product = productRepository.getById(dealDto.getProductId());
        deal.setProduct(product);
        Promotion promotion = promotionRepository.getById(dealDto.getPromotionId());
        deal.setPromotion(promotion);

        dealRepository.save(deal);
        return dealDto;
    }

    public void deleteDeal(int id){
        dealRepository.deleteById(id);
    }
}
