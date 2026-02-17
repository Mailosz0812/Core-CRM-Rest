package org.mailosz.crmrest.sales;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "crm_sale_stages")
public class SaleStage {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Column(name = "stage", nullable = false)
    @Enumerated(EnumType.STRING)
    private Stage stage;

    public SaleStage(UUID id, Stage stage) {
        this.id = id;
        this.stage = stage;
    }
    public SaleStage(){}

    public UUID getId() {
        return id;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
