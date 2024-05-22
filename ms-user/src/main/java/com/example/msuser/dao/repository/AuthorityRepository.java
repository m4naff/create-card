package com.example.msuser.dao.repository;


import com.example.msuser.dao.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    Optional<AuthorityEntity> findByName(String name);

    Optional<List<AuthorityEntity>> findAllByNameIn(Set<String> name);

}