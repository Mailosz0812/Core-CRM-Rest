package org.mailosz.crmrest.stats;

import org.mailosz.crmrest.stats.response.StatsResponse;
import org.mailosz.crmrest.stats.response.StatsTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatsService {
    private final StatsRepository statsRepo;

    public StatsService(StatsRepository statsRepo) {
        this.statsRepo = statsRepo;
    }

    public StatsResponse findMonthlyStats(){
        Map<String, BigDecimal> incomeStats = statsRepo.findMonthlyIncomeStats();
        Map<String, BigDecimal> orderVolumeStats = statsRepo.findOrderVolumeStats();
        StatsHeaderValueProjection headerProjection = statsRepo.findHeaderValues(0);
        StatsHeaderValueProjection prevHeaderProjection = statsRepo.findHeaderValues(1);

        List<BigDecimal> incomeValues = new ArrayList<>(incomeStats.values());
        BigDecimal incomeDiff;
        BigDecimal actualIncome = !incomeValues.isEmpty() ? incomeValues.get(0) : new BigDecimal(0);
        if(incomeValues.size() < 2){

            incomeDiff = calculateDifference(
                    BigDecimal.ZERO,
                    actualIncome
            );
        }
        else {
            incomeDiff = calculateDifference(incomeValues.get(1), incomeValues.get(0));
        }
        StatsTemplate incomeTemplate = new StatsTemplate(
                actualIncome.toString(),
                incomeStats,
                incomeDiff
        );

        BigDecimal volumeDiff = calculateDifference(prevHeaderProjection.getAvgOrderValue(),headerProjection.getAvgOrderValue());
        StatsTemplate orderVolume = new StatsTemplate(
                headerProjection.getAvgOrderValue().toString(),
                orderVolumeStats,
                volumeDiff
        );

        return new StatsResponse(incomeTemplate,orderVolume);
    }

    private BigDecimal calculateDifference(BigDecimal prev, BigDecimal actual){
        BigDecimal divisor;

        if(prev.compareTo(BigDecimal.ZERO) == 0){
            divisor = BigDecimal.ONE;
        }else{
            divisor = prev;
        }

        return actual
                .subtract(prev)
                .divide(divisor,4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
    }
}
