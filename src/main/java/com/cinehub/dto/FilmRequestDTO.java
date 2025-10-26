package com.cinehub.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Year;

@Data
public class FilmRequestDTO {
    @NotNull(message = "Film ID cannot be null")
    private Long filmID;
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotNull(message = "Release year cannot be null")
    @Min(value = 1888, message = "Release year must be after 1888")
    private Integer releaseYear;
    @Min(value = 30, message = "Duration must be at least 30 minute")
    @Max(value = 500, message = "Duration must be less than 500 minutes")
    private Integer durationMinutes;
    @NotBlank(message = "Synopsis cannot be null")
    private String synopsis;
    @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Rating must be at most 10.0")
    private Double rating;
    @NotNull(message = "Director ID cannot be null")
    private Long directorID;
    @NotNull(message = "Category ID cannot be null")
    private Long categoryID;

    @AssertTrue(message = "Release year must be less than or equal to the current year")
    public boolean isReleaseYearNotInFuture() {
        if (releaseYear == null) return true;
        return releaseYear <= Year.now().getValue();
    }
}