package org.mailosz.crmrest.product.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryCreationResp createCategory(@RequestBody @Valid CategoryCreationReq req){
        return this.categoryService.createCategory(req);
    }

    @GetMapping
    public CategoryCreationResp getCategoryByName(@RequestParam @NotBlank String name){
        return this.categoryService.getCategoryByName(name);
    }
}
