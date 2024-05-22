package com.example.msuser.service;

import com.example.msuser.dao.entity.AuthorityEntity;
import com.example.msuser.dao.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthorityService {
    AuthorityRepository authorityRepository;

    public void saveAuthorityList(Set<AuthorityEntity> authorityList) {
        List<AuthorityEntity> authorityListToSave = new ArrayList<>();
        authorityList.stream()
                .filter(authority -> authorityRepository.findByName(authority.getName()).isEmpty())
                .forEach(authorityListToSave::add);

        authorityRepository.saveAll(authorityListToSave);
    }

    public List<AuthorityEntity> getAllAuthorityByNameIn(Set<String> nameSet) {
        return authorityRepository.findAllByNameIn(nameSet).orElseGet(ArrayList::new);
    }

}