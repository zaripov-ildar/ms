package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.entities.User;
import ru.gb.market.core.services.OrderService;
import ru.gb.market.core.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(RuntimeException::new);//FIXME
        orderService.createOrder(user);
    }
}
