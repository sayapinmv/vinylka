package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sayap.vinylka.rest.vinyl.dao.VinylDAO;

@Controller
@RequestMapping("/vinyl")
public class VinylController {

    private VinylDAO vinylDAO;

    @Autowired
    public VinylController(VinylDAO vinylDAO) {
        this.vinylDAO = vinylDAO;
    }

    // will get all stock of lp's and then post on client view
    @GetMapping()
    public String vinyl(Model model) {

        model.addAttribute("vinyl", vinylDAO.getVinyl());

        return "vinyl/stock";
    }

    // will get singl lp by id and then  post on client view
    @GetMapping("/{id}")
    public String singleVinyl(@PathVariable("id") int id, Model model) {

        model.addAttribute("album", vinylDAO.getVinylById(id));

        return "vinyl/lp";
    }


//    @GetMapping("/")
//    public String newVinyl() {
//
//    }

    @GetMapping("/{id}/edit")
    public String editVinyl(@PathVariable("id") int id, Model model) {
        model.addAttribute("vinyl", vinylDAO.getVinylById(id));
        return "vinyl/edit";
    }

    @PatchMapping("/{id}")
    public String updateVinyl(@PathVariable("id") int id, @ModelAttribute("vinyl") Vinyl vinyl) {
        vinylDAO.update(id, vinyl);
        return "redirect:/vinyl";
    }

}
