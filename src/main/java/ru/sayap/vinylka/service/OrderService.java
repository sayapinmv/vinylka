package ru.sayap.vinylka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.model.OrderEntity;
import ru.sayap.vinylka.persistence.model.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.persistence.repository.OrderRepository;
import ru.sayap.vinylka.persistence.repository.OrderedVinylRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    OrderRepository orderRepository;
    OrderedVinylRepository orderedVinylRepository;

    @Autowired
    OrderService(OrderRepository orderRepository, OrderedVinylRepository orderedVinylRepository) {
        this.orderRepository = orderRepository;
        this.orderedVinylRepository = orderedVinylRepository;
    }

    public List<OrderEntity> getUserOrders(UserEntity userEntity) {
        return orderRepository.findByUserId(userEntity);
    }

    public OrderedVinylEntity getOrdersByVinylId(OrderEntity orderEntity) {
        return orderedVinylRepository.getByOrderId(orderEntity);
    }

    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public void cancelOrder(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderRepository.delete(orderEntity);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

}
