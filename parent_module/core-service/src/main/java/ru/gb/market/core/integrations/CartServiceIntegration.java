package ru.gb.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.CartDto;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.yaml")
public class CartServiceIntegration {
    @Value("${pathTo.cart}")
    private String pathToCart;
    private final RestTemplate restTemplate;

    public CartDto getCurrentCart() {
        return restTemplate.getForObject(pathToCart, CartDto.class);
    }

    public void clear() {
        restTemplate.getForObject(pathToCart + "/clear", Object.class);
    }
}
