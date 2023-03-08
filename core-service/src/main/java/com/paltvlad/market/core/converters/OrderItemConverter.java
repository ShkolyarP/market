package com.paltvlad.market.core.converters;

import com.paltvlad.market.api.OrderItemDto;
import com.paltvlad.market.core.entities.OrderItem;
import org.springframework.stereotype.Component;


@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        return orderItemDto;
    }
}
