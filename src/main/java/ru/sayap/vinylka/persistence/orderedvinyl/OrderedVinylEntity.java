package ru.sayap.vinylka.persistence.orderedvinyl;


import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orderd_vinyl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderedVinylEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToMany
    @JoinTable(name = "vinyl_id")
    private List<VinylEntity> vinylId = new ArrayList<>();

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
