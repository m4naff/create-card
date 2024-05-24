package com.example.msuser.service;

import com.example.msuser.dao.entity.CardEntity;
import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.CardRepository;
import com.example.msuser.dao.repository.ConsumerRepository;
import com.example.msuser.dao.repository.OrderRepository;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.dto.request.CreateCardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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
            return "Email not found";
        }
        var consumerOpt = consumerRepository.findByName(request.getConsumerName());
        if(consumerOpt.isEmpty()){
            return "Consumer not found";
        }
        UserEntity user = userOpt.get();
        ConsumerEntity consumer = consumerOpt.get();
        CardEntity card = CardEntity.builder()
                .consumer(consumer)
                .user(user)
        .build();
        cardRepository.save(card);

        consumer.setCardsId(card);
        user.setCardsId(card);
        userRepository.save(user);
        consumerRepository.save(consumer);

        orderService.createCardOrder(request);
        return "Your respond has been send!";
    }
    private String generate8DigitNumber() {
        final Random RANDOM = new Random();
        int number = RANDOM.nextInt(90000000) + 10000000;
        return String.valueOf(number);
    }

}
