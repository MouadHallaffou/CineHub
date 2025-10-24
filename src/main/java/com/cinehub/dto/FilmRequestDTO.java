package com.cinehub.dto;

import lombok.Data;

@Data
public class FilmRequestDTO {
    private Long filmID;
    private String title;
    private Integer releaseYear;
    private Integer durationMinutes;
    private String synopsis;
    private Double rating;
    private Long directorID;
    private Long categoryID;
}
