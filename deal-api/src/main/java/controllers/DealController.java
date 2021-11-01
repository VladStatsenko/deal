package controllers;

import dto.DealDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deal")
public interface DealController {

    @GetMapping
    ResponseEntity<List<DealDto>> getAllDeal();

    @GetMapping("/{id}")
    ResponseEntity getDealById(@PathVariable int id);

    @PostMapping
    ResponseEntity createDeal(@RequestBody DealDto dealDto);

    @PutMapping("/{id}")
    ResponseEntity editDeal(@RequestBody DealDto dealDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteDeal(@PathVariable int id);
}
