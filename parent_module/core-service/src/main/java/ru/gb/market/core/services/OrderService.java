package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.CartDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.User;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final RestTemplate restTemplate;
    public void createOrder(User user){
        CartDto cartDto = restTemplate.getForObject("http://localhost:8190/market-cart//api/v1/cart", CartDto.class);
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.
    }
}
