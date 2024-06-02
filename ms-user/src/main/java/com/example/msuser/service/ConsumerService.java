package com.example.msuser.service;

import com.example.msuser.dao.entity.CardEntity;
import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.repository.CardRepository;
import com.example.msuser.dao.repository.ConsumerRepository;
import com.example.msuser.dao.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.msuser.dto.constant.Status.ACTIVE;
import static com.example.msuser.dto.constant.Status.DE_ACTIVE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ConsumerService {
    ConsumerRepository consumerRepository;
    ConsumerService consumerService;
    CardRepository cardRepository;
    OrderRepository orderRepository;
    private final NotificationService notificationService;

    public List<ConsumerEntity> seeAvailableConsumers(){
        return consumerRepository.findAllByStatus(ACTIVE.getStatus());
    }
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

    public CardEntity activateCard(String name){
        var consumer = findByName(name);
        var cardOpt = cardRepository.findById(consumer.getCardsId().getId());
        var order = orderRepository.findById(consumer.getOrder().getId()).orElseThrow();
        if(cardOpt.isPresent()){
            var card = cardOpt.get();
            card.setCardNumber(generate8DigitNumber());
            card.setStatus(ACTIVE.getStatus());//it means that card has been activated and card number will be created
            consumer.setStatus(ACTIVE.getStatus()); // making it active means that consumer can activate other cards
            consumer.setCardsId(null);
            order.setStatus(DE_ACTIVE.getStatus()); //if it 1 it means that order has been completed
            order.setConsumerId(null);
            consumerRepository.save(consumer);
            cardRepository.save(card);
            orderRepository.save(order);
            return card;
        }
        return null;
    }

    public void activateCard(){
        List<CardEntity> cardEntities = fetchAllDeActiveCards();
        cardEntities.forEach(card -> {
            card.setStatus(ACTIVE.getStatus());
            card.setCardNumber(consumerService.generate8DigitNumber());
            cardRepository.save(card);
        });
    }

    private List<CardEntity> fetchAllDeActiveCards(){
        return cardRepository.findAllByStatus(DE_ACTIVE.getStatus()).orElseGet(ArrayList::new);
    }

    private String generate8DigitNumber() {
        final Random RANDOM = new Random();
        int number = RANDOM.nextInt(90000000) + 10000000;
        return String.valueOf(number);
    }

}
