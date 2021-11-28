package org.statsenko.service.rest;

import controllers.PromotionController;
import dto.PromotionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.PromotionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PromotionControllerImpl implements PromotionController {

    private final PromotionService promotionService;

    @Override
    public ResponseEntity<List<PromotionDto>> getAllPromotion() {
        return ResponseEntity.ok(promotionService.getAllPromotion());
    }

    @Override
    public ResponseEntity getPromotionById(int id) {
        return ResponseEntity.ok(promotionService.getPromotionById(id));
    }

    @Override
    public ResponseEntity createPromotion(PromotionDto promotionDto) {
        return ResponseEntity.ok(promotionService.createPromotion(promotionDto));
    }

    @Override
    public ResponseEntity editPromotion(PromotionDto promotionDto, int id) {
        return ResponseEntity.ok(promotionService.editPromotion(promotionDto, id));
    }

    @Override
    public ResponseEntity deletePromotion(int id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPromotionByProduct(int id) {
        return ResponseEntity.ok(promotionService.getPromotionByProduct(id));
    }
}
