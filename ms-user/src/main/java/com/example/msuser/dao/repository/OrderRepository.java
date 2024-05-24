package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Optional<OrderEntity> findById(Long id);
}
