package ru.sayap.vinylka.service.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartServiceMapper {

    @Mapping(target = "vinylVo", source = "vinylId")
    @Mapping(target = "cartId", source = "cartId.id")
    CartItemsVo toCartItemsVo(CartItemsEntity cartItemsEntity);

    List<CartItemsVo> toCartItemsVoList(List<CartItemsEntity> cartItemsEntity);

}
