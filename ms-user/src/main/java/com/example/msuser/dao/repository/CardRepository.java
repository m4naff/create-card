package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Long> {

    Optional<CardEntity> findById(Long id);

}
