package org.mailosz.crmrest.sales;

import org.springframework.data.jpa.domain.Specification;

public class SaleSpecification {
    public static Specification<SaleEntity> hasStage(SaleStage stage){
        return (root, query, cb) -> stage == null ? cb.conjunction() : cb.equal(root.get("stage"), stage);
    }

    public static Specification<SaleEntity> searchByTerm(String term){
        return (root, query, cb) -> {
            if (term == null || term.isBlank()) return cb.conjunction();
            String pattern = "%" + term.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("saleName")), pattern),
                    cb.like(cb.lower(root.join("client").get("name")), pattern)
            );
        };
    }
}
