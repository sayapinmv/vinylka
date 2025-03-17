package ru.sayap.vinylka.rest.cart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AddItemRequest(
        @JsonFormat(locale = "lp_id") Long vinylId,
        Integer quantity
) {
}
