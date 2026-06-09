package org.mailosz.crmrest.stats.target;

import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.crmuser.roles.Role;
import org.mailosz.crmrest.exception.types.CrmUserNotFoundException;
import org.mailosz.crmrest.exception.types.IllegalUserOperation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TargetService {

    private final TargetRepository targetRepo;
    private final UserRepository userRepo;

    public TargetService(TargetRepository targetRepo, UserRepository userRepo) {
        this.targetRepo = targetRepo;
        this.userRepo = userRepo;
    }

    public TargetResponse createTarget(TargetRequest req){
        CrmUserEntity user = this.userRepo.findCrmUserEntityById(req.getUserId())
                .orElseThrow(() -> new CrmUserNotFoundException(req.getUserId().toString()));

        if(user.getRole() != null && !user.getRole().equals(Role.SALESMAN.toString())){
            throw new IllegalUserOperation("Cannot add target to non salesman user");
        }

        LocalDate month = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);

        if(this.targetRepo.findTargetEntityByUserAndTargetMonth(user,month).isPresent()){
            throw new IllegalUserOperation("Target for this user already exists");
        }

        TargetEntity target = new TargetEntity();
        target.setUser(user);
        target.setTarget(req.getTarget());
        target.setTargetMonth(month);
        TargetEntity targetResp = this.targetRepo.save(target);

        return new TargetResponse(
                targetResp.getId().toString(),
                targetResp.getUser().getId().toString(),
                targetResp.getTargetMonth(),
                targetResp.getTarget()
        );
    }
}
