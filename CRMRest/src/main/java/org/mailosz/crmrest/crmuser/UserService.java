package org.mailosz.crmrest.crmuser;

import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.mailosz.crmrest.crmuser.response.UserCreateResp;
import org.mailosz.crmrest.crmuser.response.UserResponse;
import org.mailosz.crmrest.crmuser.roles.RoleEntity;
import org.mailosz.crmrest.crmuser.roles.RoleRepository;
import org.mailosz.crmrest.exception.types.CrmUserAlreadyExistsException;
import org.mailosz.crmrest.exception.types.CrmUserNotFoundException;
import org.mailosz.crmrest.exception.types.RoleNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final Mapper<CrmUserEntity,UserCreateResp> createMapper;


    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder, Mapper<CrmUserEntity,
            UserCreateResp> createMapper, Mapper<CrmUserEntity, UserResponse> respMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.createMapper = createMapper;
    }

    @Transactional
    public UserCreateResp createUser(UserCreateReq createReq){
        String mail = createReq.getMail();
        this.userRepo.findCrmUserEntityByMail(mail)
                .ifPresent(crmUserEntity -> { throw new CrmUserAlreadyExistsException(mail);
        });
        RoleEntity roleEntity = this.roleRepo.findRoleEntityByName(createReq.getRole().toString())
                .orElseThrow(() -> new RoleNotFoundException(createReq.getRole().toString()));

        CrmUserEntity userEntity = new CrmUserEntity();
        userEntity.setMail(mail);
        userEntity.setName(createReq.getName());
        userEntity.setSurname(createReq.getSurname());
        userEntity.setRole(roleEntity);
        userEntity.setPassword(encoder.encode(createReq.getPassword()));

        CrmUserEntity saved = userRepo.save(userEntity);
        return this.createMapper.mapFrom(saved);
    }
    public UserCreateResp getUserById(UUID userId){
        CrmUserEntity userEntity = this.userRepo.findCrmUserEntityById(userId).orElseThrow(() -> new CrmUserNotFoundException(userId.toString()));
        return this.createMapper.mapFrom(userEntity);
    }

    @Transactional
    public UUID deleteUser(UUID userId){
        CrmUserEntity userEntity = this.userRepo.findCrmUserEntityById(userId).orElseThrow(() -> new CrmUserNotFoundException(userId.toString()));
        this.userRepo.delete(userEntity);
        return userId;
    }
    public List<UserCreateResp> getAllUsers(){
        return this.userRepo.findAllNonAdminUsers().stream().map(this.createMapper::mapFrom).toList();
    }
}
