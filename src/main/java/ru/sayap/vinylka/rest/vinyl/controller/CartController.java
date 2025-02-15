package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.rest.vinyl.dao.CartDAO;
import ru.sayap.vinylka.rest.vinyl.dao.VinylDAO;
import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartDAO cartDAO;
    private final VinylDAO vinylDAO;

    @Autowired
    public CartController(CartDAO cartDAO, VinylDAO vinylDAO) {
        this.cartDAO = cartDAO;
        this.vinylDAO = vinylDAO;
    }

    @GetMapping
    public String cartList(Model model) {
        model.addAttribute("cart", cartDAO.getCart());
        return "cart/index";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable("id") int id) {
        Vinyl vinyl = vinylDAO.getVinylById(id);
        if (vinyl != null) {
            cartDAO.add(vinyl);
        }
        return "redirect:/vinyl";
    }

    @DeleteMapping("/cart/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        cartDAO.delete(id);
        return "redirect:/cart";
    }

    @DeleteMapping("/clear")
    public String clearCart() {
        cartDAO.clear();
        return "redirect:/cart";
    }
}

