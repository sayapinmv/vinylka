package ru.sayap.vinylka.service.order;

import ru.sayap.vinylka.rest.order.dto.CreateNewOrderRequest;
import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderVo> getOrders(UUID userId, Integer page, Integer size);

    OrderVo getOrder(UUID userId, Long orderId);

    void createOrder(CreateNewOrderRequest newOrder, UUID userId);

    void cancelOrder(Long orderId, UUID userId);


    }
