package com.cinehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data

public class CategoryDTO {
    @NotNull(message = "Category ID cannot be null")
    private Long categoryID;
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Description cannot be null")
    private String description;
    private List<FilmResponseDTO> films;
}
