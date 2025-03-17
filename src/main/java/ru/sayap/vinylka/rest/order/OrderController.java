package ru.sayap.vinylka.rest.order;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.order.OrderRepository;
import ru.sayap.vinylka.rest.order.dto.CancelOrderRequest;
import ru.sayap.vinylka.rest.order.dto.CreateNewOrderRequest;
import ru.sayap.vinylka.rest.order.dto.GetOrder;
import ru.sayap.vinylka.rest.order.mapper.OrderControllerMapper;
import ru.sayap.vinylka.service.order.OrderService;
import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderControllerMapper orderControllerMapper;
    private OrderService orderService;

    public OrderController(OrderService orderService, OrderControllerMapper orderControllerMapper) {
        this.orderService = orderService;
        this.orderControllerMapper = orderControllerMapper;
    }

    @GetMapping
    public List<GetOrder> getAllOrders(@RequestParam("id")  Optional<UUID> userId) {

       if (userId.isPresent()) {
           List<OrderVo> order = orderService.getOrders(userId.get());

           return orderControllerMapper.toGetOrder(order);
       }

       throw new IllegalArgumentException("userId is not present");

    }

    @GetMapping("/{id}")
    public GetOrder getOrder(@PathVariable("id") Long orderId, @RequestParam("id") Optional<UUID> userId) {

        if (userId.isPresent()) {

            OrderVo order = orderService.getOrder(userId.get(), orderId);

            return orderControllerMapper.toGetOrder(order);
        }

        throw new IllegalArgumentException("userId is not present");

    }


    @PutMapping("/{id}")
    public String createOrder(@RequestBody CreateNewOrderRequest order, @PathVariable("id") Optional<UUID> userId) {

        if (userId.isPresent()) {

            orderService.createOrder(order, userId.get());

            return "success";

        }

        throw new IllegalArgumentException("userId is not present");

    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Optional<UUID> userId, @RequestBody @NotNull CancelOrderRequest order) {

        if (userId.isPresent()) {

            orderService.cancelOrder(order.orderId(), userId.get());

        }

        throw new IllegalArgumentException("userId is not present");

    }

}
