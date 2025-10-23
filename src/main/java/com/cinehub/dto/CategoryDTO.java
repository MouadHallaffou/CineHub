package com.cinehub.dto;

import com.cinehub.model.Film;
import lombok.*;

import java.util.List;

@Data

public class CategoryDTO {
    private Long categoryID;
    private String name;
    private String description;
    private List<Film> films;
}
