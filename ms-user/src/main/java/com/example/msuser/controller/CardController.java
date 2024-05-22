package com.example.msuser.controller;

import com.example.msuser.dto.request.CreateCardRequest;
import com.example.msuser.service.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("cards")
public class CardController {
    CardService cardService;

    @PostMapping("create-card")
    public String createCard(@RequestBody CreateCardRequest request){
        return cardService.createCard(request);
    }
}
