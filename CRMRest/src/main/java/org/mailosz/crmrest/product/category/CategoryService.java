package org.mailosz.crmrest.product.category;

import org.mailosz.crmrest.exception.types.CrmCategoryAlreadyExistsException;
import org.mailosz.crmrest.exception.types.CrmCategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryCreationResp createCategory(CategoryCreationReq req){
        String name = req.getName();
        if(this.getCategory(name).isPresent()){
            throw new CrmCategoryAlreadyExistsException(name);
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(name);

        CategoryEntity savedCategory = this.categoryRepository.save(category);

        return new CategoryCreationResp(savedCategory.getId().toString(),savedCategory.getName());
    }

    public CategoryCreationResp getCategoryByName(String name){
        CategoryEntity category = this.getCategory(name).orElseThrow(() -> new CrmCategoryNotFoundException(name));
        return new CategoryCreationResp(category.getId().toString(),category.getName());
    }
    public List<CategoryCreationResp> getAllCategories(){
        return categoryRepository.findAllBy().stream().map(category -> new CategoryCreationResp(category.getId().toString(),category.getName())
        ).toList();
    }
    private Optional<CategoryEntity> getCategory(String name){
        return this.categoryRepository.findCategoryEntityByName(name);
    }

}
