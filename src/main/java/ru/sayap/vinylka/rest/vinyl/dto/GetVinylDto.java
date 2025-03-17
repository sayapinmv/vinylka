package ru.sayap.vinylka.rest.vinyl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GetVinylDto(
        @JsonFormat(locale = "lp_id") Long vinylId,
        String album,
        String author,
        BigDecimal price)
{}
