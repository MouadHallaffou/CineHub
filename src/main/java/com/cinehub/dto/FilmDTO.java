package com.cinehub.dto;

import lombok.*;


@Data

public class FilmDTO {
    private Long filmID;
    private String title;
    private Integer releaseYear;
    private Integer durationMinutes;
    private String synopsis;
    private Double rating;
    private String birthDate;
    private Long directorID;
    private Long categoryID;
    private String directorFirstName;
    private String directorLastName;
    private String categoryName;
}
