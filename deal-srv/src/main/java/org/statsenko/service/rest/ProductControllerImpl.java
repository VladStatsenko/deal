package org.statsenko.service.rest;

import controllers.ProductController;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;


    @Override
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @Override
    public ResponseEntity getProductById(int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Override
    public ResponseEntity createProduct(ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @Override
    public ResponseEntity editProduct(ProductDto productDto, int id) {
        return ResponseEntity.ok(productService.editProduct(productDto, id));
    }

    @Override
    public ResponseEntity deleteProduct(int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
