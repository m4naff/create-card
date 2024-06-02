package com.example.msuser.service;

import com.example.msuser.dao.entity.CardEntity;
import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.CardRepository;
import com.example.msuser.dao.repository.ConsumerRepository;
import com.example.msuser.dao.repository.OrderRepository;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.dto.constant.Status;
import com.example.msuser.dto.request.CreateCardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.example.msuser.dto.constant.Status.DE_ACTIVE;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE,makeFinal = true)
public class CardService {
    CardRepository cardRepository;
    UserRepository userRepository;
    ConsumerRepository consumerRepository;
    OrderService orderService;

    @Transactional
    public String createCard(CreateCardRequest request){
        var userOpt = userRepository.findByEmail(request.getEmail());
        if(userOpt.isEmpty()){
            throw new UsernameNotFoundException("Email could not be found");
        }
        var consumerOpt = consumerRepository.findByName(request.getConsumerName());
        if(consumerOpt.isEmpty()){
            throw new UsernameNotFoundException("Consumer name could not be found");
        }
        if (consumerOpt.get().getStatus() == Status.DE_ACTIVE.getStatus()){
            throw new RuntimeException("Consumer is not available");
        }
        UserEntity user = userOpt.get();
        ConsumerEntity consumer = consumerOpt.get();
        CardEntity card = CardEntity.builder()
                .consumer(consumer)
                .user(user)
        .build();
        cardRepository.save(card);

        consumer.setStatus(DE_ACTIVE.getStatus());
        consumer.setCardsId(card);
        user.setCardsId(card);
        userRepository.save(user);
        consumerRepository.save(consumer);

        orderService.createCardOrder(request);
        return "Success,your card will be ready in a short time!";
    }

}
