package controllers;

import dto.PromotionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/promotion")
public interface PromotionController {
    @GetMapping
    ResponseEntity<List<PromotionDto>> getAllPromotion();

    @GetMapping("/{id}")
    ResponseEntity getPromotionById(@PathVariable int id);

    @PostMapping
    ResponseEntity createPromotion(@RequestBody PromotionDto promotionDto);

    @PutMapping("/{id}")
    ResponseEntity editPromotion(@RequestBody PromotionDto promotionDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deletePromotion(@PathVariable int id);

    @GetMapping("product/{id}")
    ResponseEntity getPromotionByProduct(@PathVariable int id);
}
