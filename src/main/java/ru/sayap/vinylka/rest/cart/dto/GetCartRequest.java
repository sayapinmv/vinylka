package ru.sayap.vinylka.rest.cart.dto;

import java.math.BigDecimal;

public record GetCartRequest(
      Long vinylId,
      String album,
      String description,
      Integer quantity,
      BigDecimal price
) {}
