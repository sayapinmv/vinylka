package ru.sayap.vinylka.rest.vinyl.model;

public class Vinyl {

    private int id;
    private String album;

    public Vinyl() {

    }

    public Vinyl(int id, String album, float price) {
        this.id = id;
        this.album = album;
        this.price = price;
    }

    private float price;

    public void setId(int id) {
        this.id = id;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getAlbum() {
        return album;
    }

    public int getId() {
        return id;
    }

}
