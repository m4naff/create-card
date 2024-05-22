package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.RoleAuthorityEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthorityEntity, Long> {

    Optional<RoleAuthorityEntity> findByRoleIdAndAuthorityId(Long roleId, Long authorityId);

    @EntityGraph(type = FETCH, attributePaths = {"role", "authority"})
    List<RoleAuthorityEntity> findAll();

}