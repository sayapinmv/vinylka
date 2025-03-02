package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.model.CartEntity;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.service.CartService;
import ru.sayap.vinylka.persistence.service.UserService;


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

    @GetMapping
    public Set<CartItemsEntity> getCart(@RequestParam UUID userId) {

        UserEntity userEntity = userService.findById(userId);

        return cartService.viewCartItems(userEntity);
    }

}

