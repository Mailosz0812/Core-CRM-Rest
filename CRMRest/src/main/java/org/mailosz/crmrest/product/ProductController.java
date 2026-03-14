package org.mailosz.crmrest.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateReq createReq) {
        ProductResponse resp = this.productService.createProduct(createReq);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resp.getId())
                .toUri();
        return ResponseEntity.created(location).body(resp);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id){
        return this.productService.getProduct(id);
    }
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return this.productService.getAllProducts();
    }
}
