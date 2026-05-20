package org.mailosz.crmrest.product.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreationReq {

    @NotBlank
    @Size(max = 50, message = "Category name too long")
    private String name;

    public CategoryCreationReq(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

