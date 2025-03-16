package ru.sayap.vinylka.rest.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sayap.vinylka.persistence.order.OrderRepository;
import ru.sayap.vinylka.service.order.OrderService;
import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderVo> getOrders(@RequestParam UUID userId) {

       List<OrderVo> order = orderService.getOrders(userId);

       return order;

    }

}
