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

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final Mapper<CrmUserEntity,UserCreateResp> createMapper;
    private final Mapper<CrmUserEntity,UserResponse> respMapper;


    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder, Mapper<CrmUserEntity,
            UserCreateResp> createMapper, Mapper<CrmUserEntity, UserResponse> respMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.createMapper = createMapper;
        this.respMapper = respMapper;
    }

    public UserCreateResp createUser(UserCreateReq createReq, String role){
        String mail = createReq.getMail();
        this.userRepo.findCrmUserEntityByMail(mail)
                .ifPresent(crmUserEntity -> { throw new CrmUserAlreadyExistsException(mail);
        });
        RoleEntity roleEntity = this.roleRepo.findRoleEntityByName(role)
                .orElseThrow(() -> new RoleNotFoundException(role));

        CrmUserEntity userEntity = new CrmUserEntity();
        userEntity.setMail(mail);
        userEntity.setName(createReq.getName());
        userEntity.setSurname(createReq.getSurname());
        userEntity.setRole(roleEntity);
        userEntity.setPassword(encoder.encode(createReq.getPassword()));

        CrmUserEntity saved = userRepo.save(userEntity);
        return this.createMapper.mapFrom(saved);
    }
    public UserResponse getUserById(String userId){
        UUID id = UUID.fromString(userId);
        CrmUserEntity userEntity = this.userRepo.findCrmUserEntityById(id).orElseThrow(() -> new CrmUserNotFoundException(userId));
        return this.respMapper.mapFrom(userEntity);
    }
}
