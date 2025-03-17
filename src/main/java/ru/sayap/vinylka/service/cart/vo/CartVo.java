package ru.sayap.vinylka.service.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartVo {

    Long id;

    List<CartItemsVo> cartItemsVo;

}
