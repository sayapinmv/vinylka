package ru.sayap.vinylka.service.cart.vo;


import lombok.*;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemsVo {

    @Setter(AccessLevel.PROTECTED)
    Long id;

    Long cartId;

    VinylVo vinylVo;

    Integer qnty;

}
