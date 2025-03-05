package ru.sayap.vinylka.rest.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CartItem(
        @JsonProperty("lp_id") Integer lpId,
        String album,
        @JsonProperty("lp_description") String lpDescription,
        Integer quantity,
        BigDecimal price
) {
}
