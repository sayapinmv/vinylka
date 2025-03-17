package ru.sayap.vinylka.service.vinyl.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VinylVo {

    Long id;

    String author;

    String album;

    BigDecimal price;

    LocalDate editionYear;

    LocalDate  publicationYear;

    String label;

    String publicationCountry;

    Integer qnty;

    String description;

}
