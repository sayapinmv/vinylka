package ru.sayap.vinylka.service.vinyl.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link ru.sayap.vinylka.persistence.model.VinylEntity}
 */
public record VinylModel(UUID id, String author, String album, BigDecimal price, LocalDate editionYear,
                         LocalDate publicationYear, String label, String publicationCountry, String description) {
}