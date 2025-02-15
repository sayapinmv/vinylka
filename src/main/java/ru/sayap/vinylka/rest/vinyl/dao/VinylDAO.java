package ru.sayap.vinylka.rest.vinyl.dao;

import org.springframework.stereotype.Component;
import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

import java.util.ArrayList;
import java.util.List;


@Component
public class VinylDAO {

    private List<Vinyl> vinyl;

    {
        vinyl = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
                vinyl.add(new Vinyl(i, "The Beatles "+i+" album", 1000*i));
        }

    }

    public List<Vinyl> getVinyl() {
        return vinyl;
    }

    public Vinyl getVinylById(int id) {
        return vinyl.stream().filter(vinyl -> vinyl.getId() == id).findAny().orElse(null);
    }

    public void saveVinyl(Vinyl vinyl) {
        vinyl.setId(this.vinyl.size());
        this.vinyl.add(vinyl);
    }

    public void update(int id, Vinyl vinyl) {
       Vinyl vinylForUpdate = getVinylById(id);
       vinylForUpdate.setAlbum(vinyl.getAlbum());
    }

    public void delete(int id) {
        this.vinyl.removeIf(vinyl -> vinyl.getId() == id);
    }

}
