package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity,Long> {
    Optional<ConsumerEntity> findByName(String name);

    List<ConsumerEntity> findAllByStatus(int status);//if status equals 1 it means consumer is available
}
