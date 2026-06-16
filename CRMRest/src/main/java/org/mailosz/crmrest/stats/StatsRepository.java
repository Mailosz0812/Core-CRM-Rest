package org.mailosz.crmrest.stats;

import org.mailosz.crmrest.sales.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface StatsRepository extends JpaRepository<SaleEntity, UUID> {

    @Query(nativeQuery = true,
            value = "SELECT to_char(s.sale_date, 'YYYY-MM') AS monthName, " +
                    "CAST(SUM(s.sum_price) AS NUMERIC) AS calcValue " +
                    "FROM sales s " +
                    "JOIN crm_sale_stages stages ON s.stage_id = stages.id " +
                    "WHERE stages.stage = 'ZAKONCZONA' " +
                    "AND s.sale_date >= date_trunc('month', CURRENT_DATE - INTERVAL '2 month') " +
                    "AND s.sale_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                    "GROUP BY to_char(s.sale_date, 'YYYY-MM') " +
                    "ORDER BY monthName DESC")
    List<StatMapProjection> _findMonthlyIncomeStats();

    @Query(nativeQuery = true,
            value = "SELECT to_char(s.sale_date, 'YYYY-MM') AS monthName," +
                    "CAST(COUNT(s.id) AS NUMERIC) AS calcValue " +
                    "FROM sales s " +
                    "JOIN crm_sale_stages stages ON s.stage_id = stages.id " +
                    "WHERE s.sale_date >= date_trunc('month', CURRENT_DATE - INTERVAL '2 months') " +
                    "AND stages.stage = 'ZAKONCZONA' " +
                    "GROUP BY to_char(s.sale_date, 'YYYY-MM')" +
                    "ORDER BY monthName DESC")
    List<StatMapProjection> _findOrderVolumeStats();


    @Query(nativeQuery = true,
            value = "SELECT " +
                    "CAST(COALESCE(SUM(s.sum_price), 0) AS TEXT) AS incomeSum, " +
                    "CAST(COALESCE(AVG(s.sum_price), 0) AS TEXT) AS avgOrderValue " +
                    "FROM sales s " +
                    "JOIN crm_sale_stages stages ON s.stage_id = stages.id " +
                    "WHERE stages.stage = 'ZAKONCZONA' " +
                    "AND s.sale_date >= date_trunc('month', CURRENT_DATE - (:monthsBack || ' month')::INTERVAL) " +
                    "AND s.sale_date < date_trunc('month', CURRENT_DATE - ((:monthsBack - 1) || ' month')::INTERVAL)"
    )
    StatsHeaderValueProjection findHeaderValues(@Param("monthsBack") int monthBack);


    @Query(nativeQuery = true,
            value = "SELECT " +
                    "CAST(COALESCE(SUM(s.sum_price), 0) AS TEXT) AS incomeSum, " +
                    "CAST(COALESCE(AVG(s.sum_price), 0) AS TEXT) AS avgOrderValue " +
                    "FROM sales s " +
                    "JOIN crm_sale_stages stages ON s.stage_id = stages.id " +
                    "JOIN crm_users users ON users.id = s.user_id " +
                    "WHERE stages.stage = 'ZAKONCZONA' AND users.id = :userId " +
                    "AND s.sale_date >= date_trunc('month', CURRENT_DATE - (:monthsBack || ' month')::INTERVAL) " +
                    "AND s.sale_date < date_trunc('month', CURRENT_DATE - ((:monthsBack - 1) || ' month')::INTERVAL)"
    )
    StatsHeaderValueProjection findHeaderValues(@Param("monthsBack") int monthBack,@Param("userId") UUID userId);

    @Query(nativeQuery = true,
            value = "SELECT " +
                    "CAST(COUNT(s.id) FILTER (WHERE stages.stage = 'ZAKONCZONA') AS TEXT) AS wonOffers, " +
                    "CAST(COUNT(s.id) FILTER (WHERE stages.stage = 'ODRZUCONA' OR stages.stage = 'ZWROCONA') AS TEXT) AS lostOffers " +
                    "FROM sales s " +
                    "JOIN crm_sale_stages stages ON s.stage_id = stages.id " +
                    "JOIN crm_users users ON s.user_id = users.id " +
                    "WHERE s.sale_date >= date_trunc('month', CURRENT_DATE) " +
                    "AND users.id = :userId "
    )
    OffersWinRateProjection findSalesWinRate(@Param("userId") UUID userId);





    default Map<String, BigDecimal> findMonthlyIncomeStats(){
        return this._findMonthlyIncomeStats().stream().collect(Collectors.toMap(
                StatMapProjection::getMonthName,
                StatMapProjection::getCalcValue,
                (oldVal, newVal) -> oldVal,
                LinkedHashMap::new
        ));
    }

    default Map<String,BigDecimal> findOrderVolumeStats(){
        return this._findOrderVolumeStats().stream().collect(Collectors.toMap(
                StatMapProjection::getMonthName,
                StatMapProjection::getCalcValue,
                (oldVal, newVal) -> oldVal,
                LinkedHashMap::new
        ));
    }



}
