package com.example.msuser.service;

import com.example.msuser.dao.entity.CardEntity;
import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.CardRepository;
import com.example.msuser.dao.repository.ConsumerRepository;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.dto.request.CreateCardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE,makeFinal = true)
public class CardService {
    CardRepository cardRepository;
    UserRepository userRepository;
    ConsumerRepository consumerRepository;

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
                .cardNumber(RandomStringUtils.random(8))
                .consumer(consumer)
        .build();
        cardRepository.save(card);

        List<CardEntity> cards = consumer.getCardsId();
        if(cards == null){
            cards = new ArrayList<>();
        }
        cards.add(card);
        consumer.setCardsId(cards);
        user.setCardsId(card);
        userRepository.save(user);
        consumerRepository.save(consumer);
        return "Success!";
    }

}
