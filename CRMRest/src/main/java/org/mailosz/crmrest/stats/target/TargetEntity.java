package org.mailosz.crmrest.stats.target;

import jakarta.persistence.*;
import org.mailosz.crmrest.crmuser.CrmUserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_month", columnNames = {"user_id","target_month"})
},name = "crm_targets")
public class TargetEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CrmUserEntity user;

    @Column(nullable = false)
    private BigDecimal target;

    @Column(nullable = false)
    private LocalDate targetMonth;

    public TargetEntity(){}

    public TargetEntity(UUID id, CrmUserEntity userId, BigDecimal target, LocalDate targetMonth) {
        this.id = id;
        this.user = userId;
        this.target = target;
        this.targetMonth = targetMonth;
    }

    public UUID getId() {
        return id;
    }

    public CrmUserEntity getUser() {
        return user;
    }

    public void setUser(CrmUserEntity user) {
        this.user = user;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public LocalDate getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(LocalDate targetMonth) {
        this.targetMonth = targetMonth;
    }
}
