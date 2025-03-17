package ru.sayap.vinylka.service.order.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.ObjectProvider;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {

    List<OrderVo> toOrderVo(List<OrderEntity> orderEntity);

    OrderVo toOrderVo(OrderEntity orderEntity);

}
