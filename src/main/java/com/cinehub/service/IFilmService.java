package com.cinehub.service;

import com.cinehub.dto.FilmRequestDTO;
import com.cinehub.dto.FilmResponseDTO;

import java.util.List;

public interface IFilmService {

    FilmResponseDTO saveFilm(FilmRequestDTO dto);

    List<FilmResponseDTO> findAllFilms();

    FilmResponseDTO findFilmById(Long id);

    void deleteFilm(Long id);

    FilmResponseDTO updateFilm(Long id, FilmRequestDTO dto);

    FilmResponseDTO findFilmByTitle(String title);

    List<FilmResponseDTO> findFilmsByReleaseYear(int year);

    List<FilmResponseDTO> findFilmsByMinimumRating(double minRating);

    List<FilmResponseDTO> getFilmsByCategorie(String category);
}


