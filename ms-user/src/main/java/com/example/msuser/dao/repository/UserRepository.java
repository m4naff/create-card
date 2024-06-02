package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.email= :email")
    Optional<UserEntity> findByEmail(String email);

}
