package com.example.msuser.dao.repository;


import com.example.msuser.dao.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByUserIdAndRoleId(Long userId, Long roleId);

}