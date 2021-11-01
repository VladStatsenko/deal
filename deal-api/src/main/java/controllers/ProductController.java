package controllers;

import dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface ProductController {

    @GetMapping
    ResponseEntity<List<ProductDto>> getAllProduct();

    @GetMapping("/{id}")
    ResponseEntity getProductById(@PathVariable int id);

    @PostMapping
    ResponseEntity createProduct(@RequestBody ProductDto productDto);

    @PutMapping("/{id}")
    ResponseEntity editProduct(@RequestBody ProductDto productDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteProduct(@PathVariable int id);
}
