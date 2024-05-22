package com.example.msuser.service.fake;

import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.service.ConsumerService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AutoSaveConsumerData {
    ConsumerService consumerService;

//    @PostConstruct
    public void saveConsumers(){
        ConsumerEntity consumer1 = ConsumerEntity.builder()
                .name("Akif")
                .build();
        ConsumerEntity consumer2 = ConsumerEntity.builder()
                .name("Murad")
                .build();
        ConsumerEntity consumer3 = ConsumerEntity.builder()
                .name("Qasim")
                .build();
        consumerService.saveConsumer(consumer1);
        consumerService.saveConsumer(consumer2);
        consumerService.saveConsumer(consumer3);
    }
}
