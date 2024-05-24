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

import java.util.List;

import static com.example.msuser.dto.constant.Status.ACTIVE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ConsumerService {
    ConsumerRepository consumerRepository;
    CardRepository cardRepository;
    OrderRepository orderRepository;
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
            card.setStatus(ACTIVE.getStatus());//it means that card has been activated and card number will be created
            consumer.setStatus(ACTIVE.getStatus()); // making it active means that consumer can activate other cards
            consumer.setCardsId(null);
            order.setStatus(ACTIVE.getStatus()); //if it 1 it means that order has been completed
            consumerRepository.save(consumer);
            cardRepository.save(card);
            return card;
        }
        return null;
    }

}
