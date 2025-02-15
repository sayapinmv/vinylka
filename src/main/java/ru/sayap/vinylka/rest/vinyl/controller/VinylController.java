package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.rest.vinyl.dao.VinylDAO;
import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

@Controller
@RequestMapping("/vinyl")
public class VinylController {

    private VinylDAO vinylDAO;

    @Autowired
    public VinylController(VinylDAO vinylDAO) {
        this.vinylDAO = vinylDAO;
    }

    @GetMapping()
    public String vinyl(Model model) {
        model.addAttribute("vinyl", vinylDAO.getVinyl());
        return "vinyl/index";
    }

    @GetMapping("/{id}")
    public String singleVinyl(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", vinylDAO.getVinylById(id));
        return "vinyl/item";
    }

    @GetMapping("/add")
    public String newVinyl(Model model) {
        model.addAttribute("vinyl", new Vinyl());
        return "vinyl/add";
    }

    @PostMapping()
    public String add(@ModelAttribute("vinyl") Vinyl vinyl) {
        vinylDAO.saveVinyl(vinyl);
        return "redirect:/vinyl";
    }

    @GetMapping("/{id}/edit")
    public String editVinyl(@PathVariable("id") int id, Model model) {
        model.addAttribute("vinyl", vinylDAO.getVinylById(id));
        return "vinyl/edit";
    }

    @PatchMapping("/{id}")
    public String updateVinyl(@ModelAttribute("vinyl") Vinyl vinyl, @PathVariable("id") int id) {
        vinylDAO.update(id, vinyl);
        return "redirect:/vinyl";
    }

    @DeleteMapping("/{id}")
    public String deleteVinyl(@PathVariable("id") int id) {
        vinylDAO.delete(id);
        return "redirect:/vinyl";
    }

}
