package com.cinehub.dto;

import lombok.Data;

@Data
public class FilmResponseDTO {
    private String title;
    private Integer releaseYear;
    private Integer durationMinutes;
    private String synopsis;
    private Double rating;
    private String directorFirstName;
    private String directorLastName;
    private String birthDate;
    private String categoryName;
}
