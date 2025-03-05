package ru.sayap.vinylka.rest.cart.dto;

public record GetCartResponse(
        List<CartItem> items
) {
}
