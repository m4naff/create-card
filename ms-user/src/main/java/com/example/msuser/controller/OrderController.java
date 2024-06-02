package com.example.msuser.controller;

import com.example.msuser.dao.entity.OrderEntity;
import com.example.msuser.dto.pagination.PageContainer;
import com.example.msuser.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {
    OrderService orderService;

    @GetMapping("all-orders")
    public PageContainer<List<OrderEntity>> getAllOrders(@RequestParam(defaultValue = "0")int page,
                                                         @RequestParam(defaultValue = "10")int size){
        return orderService.getOrderList(page,size);
    }
}
