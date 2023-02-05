package ru.gb.market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.yaml")
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${pathTo.products}")
    private String pathToProduct;

    public Optional<ProductDto> findById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(pathToProduct + "/" + id, ProductDto.class));
    }
}
