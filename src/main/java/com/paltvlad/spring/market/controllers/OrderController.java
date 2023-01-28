package com.paltvlad.spring.market.controllers;


import com.paltvlad.spring.market.entities.User;
import com.paltvlad.spring.market.services.OrderService;
import com.paltvlad.spring.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
//
//    private final UserService userService;
//    private final OrderService orderService;


// TODO: дописать
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createOrder(Principal principal){
//        User user = userService.findByUsername(principal.getName()).orElseThrow(()-> new RuntimeException());
//        orderService.createOrder(user);
//    }

}
