package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    // ...

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(Principal principal) {
        // ...
    }
}
