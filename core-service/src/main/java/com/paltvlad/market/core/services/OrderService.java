package com.paltvlad.market.core.services;

import com.paltvlad.market.api.CartDto;
import com.paltvlad.market.core.entities.Order;
import com.paltvlad.market.core.entities.OrderItem;
import com.paltvlad.market.core.integrations.CartServiceIntegration;
import com.paltvlad.market.core.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setOrderItem(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(

                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clearCart();
    }
}
