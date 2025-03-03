package ru.sayap.vinylka.rest.vinyl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.service.vinyl.VinylService;

import java.util.List;

@RestController
@RequestMapping("/vinyl")
public class VinylController {

    private final VinylService vinylService;

    @Autowired
    public VinylController(VinylService vinylService) {
        this.vinylService = vinylService;
    }

//    @PostMapping("/vinyl")
//    public ResponseEntity<?> addVinyl(@RequestBody VinylEntity vinylEntity) {
//        return new ResponseEntity<>(vinylService.save(vinylEntity), HttpStatus.CREATED);
//    }

    @GetMapping
    public List<VinylEntity> getAllVinyls() {
        return vinylService.findAll();
    }

//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        List<VinylEntity> vinyls = vinylRepository.findAll();
//        return !vinyls.isEmpty() ? new ResponseEntity<>(vinyls, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @GetMapping()
//    public String vinyl(Model model) {
//        model.addAttribute("vinyl", vinylDAO.getVinyl());
//        return "vinyl/index";
//    }
//
//    @GetMapping("/{id}")
//    public String singleVinyl(@PathVariable("id") int id, Model model) {
//        model.addAttribute("album", vinylDAO.getVinylById(id));
//        return "vinyl/item";
//    }
//
//    @GetMapping("/add")
//    public String newVinyl(Model model) {
//        model.addAttribute("vinyl", new Vinyl());
//        return "vinyl/add";
//    }
//
//    @PostMapping()
//    public String add(@ModelAttribute("vinyl") Vinyl vinyl) {
//        vinylDAO.saveVinyl(vinyl);
//        return "redirect:/vinyl";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editVinyl(@PathVariable("id") int id, Model model) {
//        model.addAttribute("vinyl", vinylDAO.getVinylById(id));
//        return "vinyl/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String updateVinyl(@ModelAttribute("vinyl") Vinyl vinyl, @PathVariable("id") int id) {
//        vinylDAO.update(id, vinyl);
//        return "redirect:/vinyl";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteVinyl(@PathVariable("id") int id) {
//        vinylDAO.delete(id);
//        return "redirect:/vinyl";
//    }

}
