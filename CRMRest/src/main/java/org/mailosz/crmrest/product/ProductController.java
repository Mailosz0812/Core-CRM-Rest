package org.mailosz.crmrest.product;

import org.springframework.web.bind.annotation.*;

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
    public Product getProduct(@PathVariable UUID id){
        return this.productService.getProduct(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }
}
