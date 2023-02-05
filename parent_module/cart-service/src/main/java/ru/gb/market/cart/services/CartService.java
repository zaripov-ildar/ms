package ru.gb.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.ProductDto;
import ru.gb.market.cart.integrations.ProductServiceIntegration;
import ru.gb.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.lang.module.ResolutionException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId).orElseThrow(()->new ResolutionException("Product not found"));
        cart.add(p);
    }
}
