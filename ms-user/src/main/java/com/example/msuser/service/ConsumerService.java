package com.example.msuser.service;

import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.repository.ConsumerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ConsumerService {
    ConsumerRepository consumerRepository;
    public List<ConsumerEntity> findAll(){
        return consumerRepository.findAll();
    }

    public ConsumerEntity findByName(String name){
        return consumerRepository.findByName(name).orElseThrow(()-> new UsernameNotFoundException("Consumer not found"));
    }

    public void saveConsumer(ConsumerEntity consumer1){
        ConsumerEntity consumer = ConsumerEntity.builder()
                        .name(consumer1.getName())
                .build();
        consumerRepository.save(consumer);
    }

}
