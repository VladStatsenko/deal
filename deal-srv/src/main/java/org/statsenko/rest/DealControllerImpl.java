package org.statsenko.rest;

import controllers.DealController;
import dto.DealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.DealService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DealControllerImpl implements DealController {

    private final DealService dealService;

    @Override
    public ResponseEntity<List<DealDto>> getAllDeal() {
        return ResponseEntity.ok(dealService.getAllDeal());
    }

    @Override
    public ResponseEntity getDealById(int id) {
        return ResponseEntity.ok(dealService.getDealById(id));
    }

    @Override
    public ResponseEntity createDeal(DealDto dealDto) {
        return ResponseEntity.ok(dealService.createDeal(dealDto));
    }

    @Override
    public ResponseEntity editDeal(DealDto dealDto, int id) {
        return ResponseEntity.ok(dealService.editDeal(dealDto, id));
    }

    @Override
    public ResponseEntity deleteDeal(int id) {
        dealService.deleteDeal(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllDealWithPromotion(int id) {
        return ResponseEntity.ok(dealService.getAllDealWithPromotion(id));
    }
}
