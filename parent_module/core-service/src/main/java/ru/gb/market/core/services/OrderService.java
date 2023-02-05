package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.api.CartDto;
import ru.gb.market.core.entities.Order;
import ru.gb.market.core.entities.OrderItem;
import ru.gb.market.core.entities.User;
import ru.gb.market.core.exceptions.ResourceNotFoundException;
import ru.gb.market.core.integrations.CartServiceIntegration;
import ru.gb.market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                        cartItemDto -> new OrderItem(
                                order,
                                productService.findById(cartItemDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Can't find product #" + cartItemDto.getProductId())),
                                cartItemDto.getPricePerProduct(),
                                cartItemDto.getPrice(),
                                cartItemDto.getQuantity()
                        )
                ).collect(Collectors.toList())
        );
        orderRepository.save(order);
        cartServiceIntegration.clear();
    }
}
