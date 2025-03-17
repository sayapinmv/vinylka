package ru.sayap.vinylka.rest.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sayap.vinylka.rest.cart.dto.GetCartRequest;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartControllerMapper {

    List<GetCartRequest> toGetCartRequest(List<CartItemsVo> cartItemsVo);

    @Mapping(source = "vinylVo.id", target = "vinylId")
    @Mapping(source = "vinylVo.album", target = "album")
    @Mapping(source = "vinylVo.description", target = "description")
    @Mapping(source = "qnty", target = "quantity")
    @Mapping(source = "vinylVo.price", target = "price")
    GetCartRequest toGetCartRequest(CartItemsVo cartItemsVo);

}
