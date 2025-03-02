package ru.sayap.vinylka.rest.vinyl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sayap.vinylka.persistence.model.OrderEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.service.OrderService;
import ru.sayap.vinylka.persistence.service.UserService;
import ru.sayap.vinylka.persistence.service.VinylService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public List<OrderEntity> getOrders(@PathVariable(name = "user_id") UUID userId) {

        UserEntity userEntity = userService.findById(userId);

        return orderService.getUserOrders(userEntity);
    }

}
