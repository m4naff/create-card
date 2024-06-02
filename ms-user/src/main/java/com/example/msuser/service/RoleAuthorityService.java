package com.example.msuser.service;

import com.example.msuser.dao.entity.AuthorityEntity;
import com.example.msuser.dao.entity.RoleAuthorityEntity;
import com.example.msuser.dao.entity.RoleEntity;
import com.example.msuser.dao.repository.RoleAuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RoleAuthorityService {
    RoleAuthorityRepository roleAuthorityRepository;
    AuthorityService authorityService;
    RoleService roleService;

    public Optional<RoleAuthorityEntity> findByRoleIdAndAuthorityId(Long roleId, Long authorityId) {
        return roleAuthorityRepository.findByRoleIdAndAuthorityId(roleId, authorityId);
    }

    public void saveRoleWithAuthorityList(String roleName, Set<String> authorityNameSet) {
        List<RoleAuthorityEntity> roleAuthorityList = new ArrayList<>();
        authorityNameSet = authorityNameSet.stream().map(String::toLowerCase).collect(Collectors.toSet());
        List<AuthorityEntity> authorityList = authorityService.getAllAuthorityByNameIn(authorityNameSet);
        RoleEntity role = roleService.getRoleByName(roleName.toUpperCase());

        authorityList.stream().filter(authority -> findByRoleIdAndAuthorityId(role.getId(), authority.getId()).isEmpty())
                .forEach(authority -> roleAuthorityList.add(RoleAuthorityEntity.builder()
                        .authority(authority)
                        .role(role)
                        .build()));

        if (roleAuthorityList.isEmpty()) return;

        roleAuthorityRepository.saveAll(roleAuthorityList);
    }

    @PostConstruct
    private void saveAuthorities() {
        AuthorityEntity writeAuthority = AuthorityEntity.builder()
                .name("write")
                .build();

        AuthorityEntity readAuthority = AuthorityEntity.builder()
                .name("read")
                .build();

        AuthorityEntity deleteAuthority = AuthorityEntity.builder()
                .name("delete")
                .build();

        AuthorityEntity updateAuthority = AuthorityEntity.builder()
                .name("update")
                .build();

        authorityService.saveAuthorityList(Set.of(readAuthority, updateAuthority, deleteAuthority, writeAuthority));
    }

}