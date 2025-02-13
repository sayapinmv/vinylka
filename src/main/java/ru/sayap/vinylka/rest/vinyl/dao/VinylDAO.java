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
                vinyl.add(new Vinyl(i+1, "The Beatles "+i+" album", 1000*i));
        }

    }

    public List<Vinyl> getVinyl() {
        return vinyl;
    }

    public Vinyl getVinylById(int id) {
        return vinyl.stream().filter(vinyl -> vinyl.getId() == id).findAny().orElse(null);
    }

    public void saveVinyl(Vinyl vinyl) {
        int newId = this.vinyl.size();
        vinyl.setId(++newId);
    }

}
