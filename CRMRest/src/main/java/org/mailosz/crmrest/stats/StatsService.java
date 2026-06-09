package org.mailosz.crmrest.stats;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.crmuser.roles.Role;
import org.mailosz.crmrest.exception.types.CrmUserNotFoundException;
import org.mailosz.crmrest.exception.types.InsufficientPrivilegesException;
import org.mailosz.crmrest.stats.response.SalesmanStats;
import org.mailosz.crmrest.stats.response.StatsResponse;
import org.mailosz.crmrest.stats.response.StatsTemplate;
import org.mailosz.crmrest.stats.target.TargetEntity;
import org.mailosz.crmrest.stats.target.TargetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StatsService {
    private final StatsRepository statsRepo;
    private final UserRepository userRepository;
    private final TargetRepository targetRepository;

    public StatsService(StatsRepository statsRepo, UserRepository userRepository, TargetRepository targetRepository) {
        this.statsRepo = statsRepo;
        this.userRepository = userRepository;
        this.targetRepository = targetRepository;
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

    public SalesmanStats findSalesmanStats(String username){
        CrmUserEntity user = this.userRepository.findCrmUserEntityByMail(username).orElseThrow(() -> new CrmUserNotFoundException(username));
        if(!Role.SALESMAN.toString().equals(user.getRole())){
            throw new InsufficientPrivilegesException("Invalid privileges");
        }
        StatsHeaderValueProjection headerValues = this.statsRepo.findHeaderValues(0,user.getId());

        LocalDate month = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
        TargetEntity target = this.targetRepository.findTargetEntityByUserAndTargetMonth(user,month)
                .orElseGet(() -> new TargetEntity(UUID.randomUUID(),user,BigDecimal.ZERO,month));

        OffersWinRateProjection winRate = this.statsRepo.findSalesWinRate(user.getId());

        return new SalesmanStats(
                headerValues.getIncomeSum().toString(),
                target.getTarget().toString(),
                winRate.getWonOffers(),
                winRate.getLostOffers(),
                headerValues.getAvgOrderValue().setScale(4,RoundingMode.HALF_UP).toString());
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
