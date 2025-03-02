package ru.sayap.vinylka.persistence.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="orderd_vinyl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderedVinylEntity {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "vinyl_id", nullable = false)
    private VinylEntity vinylId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "edition_year")
    private LocalDate editionYear;

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Column(name = "label")
    private String label;

    @Column(name = "publication_country")
    private String publicationCountry;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

}
