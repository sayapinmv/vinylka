package ru.sayap.vinylka.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="vinyl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VinylEntity {

    @Id
    @GeneratedValue
    UUID id;

    @Column(name = "author")
    String author;

    @Column(name = "album")
    String album;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "edition_year")
    LocalDate editionYear;

    @Column(name = "publication_year")
    LocalDate  publicationYear;

    @Column(name = "label")
    String label;

    @Column(name = "publication_country")
    String publicationCountry;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "vinylId")
    List<CartItemsEntity> cartItemsEntity;

    @OneToMany(mappedBy = "vinylId")
    List<OrderedVinylEntity> orderedVinylEntity;

}
