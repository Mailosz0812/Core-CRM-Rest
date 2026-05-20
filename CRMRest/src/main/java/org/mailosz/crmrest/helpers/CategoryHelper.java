package org.mailosz.crmrest.helpers;

import org.mailosz.crmrest.product.category.CategoryEntity;
import org.mailosz.crmrest.product.category.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CategoryHelper {
    private final CategoryRepository categoryRepo;

    public CategoryHelper(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Map<UUID, CategoryEntity> getCategoriesMap(){
        return this.categoryRepo.findAllBy().stream()
                .collect(Collectors.toMap(CategoryEntity::getId, categoryEntity -> categoryEntity));
    }


}
