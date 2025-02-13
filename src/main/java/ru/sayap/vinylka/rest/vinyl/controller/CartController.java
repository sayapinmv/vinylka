package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sayap.vinylka.rest.vinyl.dao.VinylDAO;
import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

@Controller
@RequestMapping("/cart")
public class CartController {

    VinylDAO vinylDAO = new VinylDAO();

    @GetMapping("/add_vinyl")
    public String newVinyl(Model model) {
        model.addAttribute("vinyl", new Vinyl());

        return "cart/add_vinyl";
    }

    @PostMapping()
    public String addNewToCart(@ModelAttribute("vinyl") Vinyl vinyl) {
        vinylDAO.saveVinyl(vinyl);
        return "redirect:/add_vinyl";
    }


}
