package ru.gb.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.CartDto;
import ru.gb.market.cart.converters.CartConverter;
import ru.gb.market.cart.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.getCurrentCart().clear();
    }


}
