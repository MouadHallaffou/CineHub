package com.cinehub.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DirectorDTO {
    private Long directorID;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationality;
    private String biography;
    private List<FilmDTO> films;
}
