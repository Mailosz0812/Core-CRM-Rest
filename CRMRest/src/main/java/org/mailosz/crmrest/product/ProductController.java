package org.mailosz.crmrest.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable UUID id){
        return this.productService.getProduct(id);
    }
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return this.productService.getAllProducts();
    }
}
