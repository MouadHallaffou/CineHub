package com.cinehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DirectorDTO {
    @NotNull(message = "Director ID cannot be null")
    private Long directorID;
    @NotBlank(message = "First name cannot be null")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @NotBlank(message = "Birth date cannot be null")
    private String birthDate;
    @NotBlank(message = "Nationality cannot be null")
    private String nationality;
    @NotBlank(message = "Biography cannot be null")
    private String biography;
    private List<FilmResponseDTO> films;
}
