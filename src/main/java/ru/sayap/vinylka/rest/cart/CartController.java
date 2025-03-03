package ru.sayap.vinylka.rest.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.rest.cart.dto.GetCartResponse;
import ru.sayap.vinylka.service.cart.CartService;
import ru.sayap.vinylka.service.UserService;


import java.util.Set;
import java.util.UUID;
//import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;
    UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public GetCartResponse getCart(@PathVariable(name = "user_id") UUID userId) {

        UserEntity userEntity = userService.findById(userId);

        return cartService.viewCartItems(userEntity);
    }

}

