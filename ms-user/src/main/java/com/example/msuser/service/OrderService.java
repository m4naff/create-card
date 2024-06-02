package com.example.msuser.service;

import com.example.msuser.dao.entity.OrderEntity;
import com.example.msuser.dao.repository.CardRepository;
import com.example.msuser.dao.repository.ConsumerRepository;
import com.example.msuser.dao.repository.OrderRepository;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.dto.client.request.NotificationsRequest;
import com.example.msuser.dto.request.CreateCardRequest;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    CardRepository cardRepository;
    ConsumerRepository consumerRepository;
    UserRepository userRepository;
    NotificationService notificationService;

    @Transactional
    public void createCardOrder(CreateCardRequest request){
        var consumerOpt = consumerRepository.findByName(request.getConsumerName());
        var userOpt = userRepository.findByEmail(request.getEmail());
        var consumer = consumerOpt.get();
        var card = cardRepository.findById(consumer.getCardsId().getId()).orElseThrow();
        var order = OrderEntity.builder()
                .cardId(card)
                .consumerId(consumer)
                .userId(userOpt.get())
                .build();
        notificationService.sendNotification(NotificationsRequest.builder()
                        .message("There is a order for new card!")
                        .consumerId(consumer.getId())
                .build());
        orderRepository.save(order);
    }



}
