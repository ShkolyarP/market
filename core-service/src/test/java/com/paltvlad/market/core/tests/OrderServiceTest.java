package com.paltvlad.market.core.tests;

import com.paltvlad.market.api.CartDto;
import com.paltvlad.market.api.CartItemDto;
import com.paltvlad.market.core.entities.Category;
import com.paltvlad.market.core.entities.Product;
import com.paltvlad.market.core.integrations.CartServiceIntegration;
import com.paltvlad.market.core.repositories.OrderRepository;

import com.paltvlad.market.core.services.OrderService;
import com.paltvlad.market.core.services.ProductService;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        List<CartItemDto>items = new ArrayList<>();
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProductTitle("Bread");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(120));
        cartItemDto.setQuantity(2);
        cartItemDto.setPrice(BigDecimal.valueOf(240));
        cartItemDto.setProductId(23322L);
        cartDto.setTotalPrice(BigDecimal.valueOf(240));
        cartDto.setItems(List.of(cartItemDto));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart(null);

        Category category = new Category();
        category.setId(1L);
        category.setTitle("food");

        Product product = new Product();
        product.setId(23322L);
        product.setPrice(BigDecimal.valueOf(120));
        product.setTitle("Bread");
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(23322L);

        orderService.createOrder("Bob");

        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
