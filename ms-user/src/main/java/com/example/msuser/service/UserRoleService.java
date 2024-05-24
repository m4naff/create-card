package com.example.msuser.service;

import com.example.msuser.dao.entity.RoleEntity;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.entity.UserRoleEntity;
import com.example.msuser.dao.repository.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserRoleService {
    UserRoleRepository userRoleRepository;
    UserService userService;
    RoleService roleService;

    public Optional<UserRoleEntity> findByUserIdAndRoleId(Long userId,Long roleId){
        return userRoleRepository.findByUserIdAndRoleId(userId,roleId);
    }

    public void saveUserWithRole(String email,String roleName){
        UserEntity userFromDB = userService.findUserByEmail(email);
        RoleEntity role = roleService.getRoleByName(roleName.toUpperCase());

        if (Objects.isNull(userFromDB)){
            return;
        }
        if(findByUserIdAndRoleId(userFromDB.getId(),role.getId()).isEmpty()){
            userRoleRepository.save(UserRoleEntity.builder()
                            .role(role)
                            .user(userFromDB)
                    .build());
        }
    }

}
