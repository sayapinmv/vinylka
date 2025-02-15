package ru.sayap.vinylka.rest.vinyl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDAO {
    private final List<Vinyl> cart = new ArrayList<>();

    public List<Vinyl> getCart() {
        return cart;
    }

    public void add(Vinyl vinyl) {
        cart.add(vinyl);
    }

    public void delete(int id) {
        cart.removeIf(vinyl -> vinyl.getId() == id);
    }

    public void clear() {
        cart.clear();
    }
}