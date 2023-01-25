package com.paltvlad.spring.market.services;


import com.paltvlad.spring.market.converters.ProductConverter;
import com.paltvlad.spring.market.dtos.CartItem;
import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.dtos.Cart;
import com.paltvlad.spring.market.entities.Product;
import com.paltvlad.spring.market.exeptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void addToCard(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        ProductDto productDto = productConverter.EntityToDto(product);
        tempCart.addToCart(productDto);


    }


    public void changeQuantity(Long id, Integer delta) {

        CartItem item = tempCart.findById(id);
        if (!(item.getQuantity() == 1 && delta == -1)) {

            item.setQuantity(item.getQuantity() + delta);
            item.setPrice(item.getPrice() + item.getPricePerProduct() * delta);
        }
        tempCart.recalculate();
    }

    public void deleteById(Long id) {

        tempCart.deleteById(id);
    }

    public void deleteAllFromCart() {
        tempCart.deleteAllFromCart();
    }
}
