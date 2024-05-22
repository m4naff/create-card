package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity,Long> {
    Optional<ConsumerEntity> findByName(String name);

    Optional<ConsumerEntity> findAllByStatus(int status);//if status equals 1 it means consumer is available
}
