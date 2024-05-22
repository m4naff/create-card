package com.example.msuser.service;

import com.example.msuser.dao.entity.RoleEntity;
import com.example.msuser.dao.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;

    public RoleEntity getRoleByName(String name){
        return roleRepository.findByName(name).orElseThrow(()->new UsernameNotFoundException("Not found"));
    }

    public void saveRoleList(Set<RoleEntity> roleList){
        List<RoleEntity> roleListToSave = new ArrayList<>();

        roleList.stream().filter(role -> roleRepository.findByName(role.getName()).isEmpty())
                .forEach(roleListToSave::add);

        roleRepository.saveAll(roleListToSave);
    }

}
