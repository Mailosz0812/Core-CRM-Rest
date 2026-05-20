package org.mailosz.crmrest.product.category;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    public CategoryEntity(){}

    public CategoryEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
