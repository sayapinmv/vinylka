package ru.sayap.vinylka.rest.vinyl;


import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.GetVinylDto;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.mapper.VinylControllerMapper;
import ru.sayap.vinylka.service.vinyl.VinylService;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vinyl")
public class VinylController {

    private final VinylService vinylService;
    private final VinylControllerMapper vinylControllerMapper;

    @Autowired
    public VinylController(VinylService vinylService, VinylControllerMapper vinylControllerMapper) {
        this.vinylService = vinylService;
        this.vinylControllerMapper = vinylControllerMapper;
    }


    @GetMapping
    public ResponseEntity<List<GetVinylDto>> getAllVinyl(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        if (page.isPresent() && size.isPresent()) {
            List<VinylVo> getVinylVo = vinylService.getVinylList();

            //vinylVo.get(0).setAlbum("MatBoT the best man in the World");

//                List<GetVinylDto> vinylDto = getVinylVo.stream()
//                        .map(vinylControllerMapper::toVinylDto)
//                        .toList();

            List<GetVinylDto> getVinylDto = new ArrayList<>();

            for (int i = 0; i < getVinylVo.size(); i++) {

                getVinylDto.add(vinylControllerMapper.toGetVinylDto(getVinylVo.get(i)));

            }

            return ResponseEntity.ok(getVinylDto);
        }

        throw new IllegalArgumentException("Required parameter (page or size) not provided, unfortunately");

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetVinylDto> getVinyl(@PathVariable("id") Long id) {

        VinylVo vinylVo = vinylService.getVinyl(id);

        return ResponseEntity.ok(vinylControllerMapper.toGetVinylDto(vinylVo));
    }


    @PutMapping
    public ResponseEntity<GetVinylDto> createVinyl(@RequestBody @NotNull CreateVinylRequest body) {

        VinylVo vinylVo = vinylService.create(body);

        return ResponseEntity.created(URI.create("/%s".formatted(vinylVo.getId())))
                .body(vinylControllerMapper.toGetVinylDto(vinylVo));


    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetVinylDto> updateVinyl(@PathVariable("id") Long id, @RequestBody @NotNull UpdateVinylRequest body) {

        VinylVo updatedVinylVo = vinylService.update(id, body);

        return ResponseEntity.created(URI.create("/%s".formatted(updatedVinylVo.getId())))
                .body(vinylControllerMapper.toGetVinylDto(updatedVinylVo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVinyl(@PathVariable("id") @NotNull Long id) {

        vinylService.deleteById(id);
        return ResponseEntity.ok().body("vinyl deleted successfully");

    }

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
