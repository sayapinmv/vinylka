package ru.sayap.vinylka.rest.vinyl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateVinylRequest(
        String album,
        String author,
        BigDecimal price,
        @JsonFormat(locale = "edition_year") LocalDate editionYear,
        @JsonFormat(locale = "publication_year") LocalDate publicationYear,
        String label,
        @JsonFormat(locale = "publication_country") String publicationCountry,
        @JsonFormat(locale = "quantity") Integer qnty,
        String description
){}
