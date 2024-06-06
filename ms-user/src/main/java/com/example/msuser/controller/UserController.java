package com.example.msuser.controller;

import com.example.msuser.dao.entity.ConsumerEntity;
import com.example.msuser.dao.entity.OrderEntity;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dto.pagination.PageContainer;
import com.example.msuser.dto.request.CreateCardRequest;
import com.example.msuser.dto.request.SignInRequest;
import com.example.msuser.dto.request.SignUpRequest;
import com.example.msuser.dto.response.AuthenticationResponse;
import com.example.msuser.service.CardService;
import com.example.msuser.service.ConsumerService;
import com.example.msuser.service.OrderService;
import com.example.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final ConsumerService consumerService;
    private final CardService cardService;
    private final OrderService orderService;

    @GetMapping("search")
    public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
    }

    @GetMapping("sign-in")
    public AuthenticationResponse signIn(@RequestBody SignInRequest request){
        return userService.signIn(request);
    }

    @GetMapping("available-consumers")
    public List<ConsumerEntity> seeAvailableConsumer(){
        return consumerService.seeAvailableConsumers();
    }

    @PostMapping("create-card")
    public String createCard(@RequestBody CreateCardRequest request){
        return cardService.createCard(request);
    }

    @GetMapping("all-orders")
    public PageContainer<List<OrderEntity>> getAllOrders(@RequestParam(defaultValue = "0")int page,
                                                         @RequestParam(defaultValue = "10")int size){
        return orderService.getOrderList(page,size);
    }
}
