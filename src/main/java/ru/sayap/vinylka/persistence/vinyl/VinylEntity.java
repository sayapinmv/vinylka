package ru.sayap.vinylka.persistence.vinyl;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
//import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
//import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="vinyl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VinylEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "album")
    private String album;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "edition_year")
    private LocalDate editionYear;

    @Column(name = "publication_year")
    private LocalDate  publicationYear;

    @Column(name = "label")
    private String label;

    @Column(name = "publication_country")
    private String publicationCountry;

    @Column(name = "quantity")
    private Integer qnty;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "vinylId")
    @ToString.Exclude
    List<CartItemsEntity> cartItemsEntity;
//
//    @OneToMany(mappedBy = "vinylId")
//    @ToString.Exclude
//    List<OrderedVinylEntity> orderedVinylEntity;

}
