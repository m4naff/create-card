package com.example.msuser.controller;

import com.example.msuser.dao.entity.CardEntity;
import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.service.ConsumerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("consumers")
public class ConsumerController {
    ConsumerService consumerService;

    @PutMapping("activate-card/{name}")
    public CardEntity activateCard(@PathVariable String name){
        return consumerService.activateCard(name);
    }

    @GetMapping("available-consumers")
    public List<ConsumerEntity> seeAvailableConsumer(){
        return consumerService.seeAvailableConsumers();
    }
}
