package com.cinehub.dto;

import com.cinehub.model.Category;
import com.cinehub.model.Director;
import lombok.*;

@Data

public class FilmDTO {
    private Long filmID;
    private String title;
    private Integer releaseYear;
    private Integer durationMinutes;
    private String synopsis;
    private Double rating;
    private Director director;
    private Category category;
}
