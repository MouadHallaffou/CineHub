package com.cinehub.service;

import com.cinehub.dto.FilmDTO;
import com.cinehub.exception.FilmException;
import com.cinehub.mapper.FilmMapper;
import com.cinehub.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    // Constructor
    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    // create a film
    public FilmDTO saveFilm(FilmDTO dto) {
        var film = filmMapper.toEntity(dto);
        var saved = filmRepository.save(film);
        return filmMapper.toDTO(saved);
    }

    // get all films
    public List<FilmDTO> findAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    // get a film by ID
    public FilmDTO findFilmById(Long id) {
        var film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmException(id));
        return filmMapper.toDTO(film);
    }

    // delete a film by ID
    public void deleteFilm(Long id) {
        if (!filmRepository.existsById(id)) {
            throw new FilmException(id);
        }
        filmRepository.deleteById(id);
    }

    // update a film by ID
    public FilmDTO updateFilm(Long id, FilmDTO dto) {
        if (!filmRepository.existsById(id)) {
            throw new FilmException(id);
        }
        var film = filmMapper.toEntity(dto);
        film.setFilmID(id);
        var updated = filmRepository.save(film);
        return filmMapper.toDTO(updated);
    }

    // rechercher un film par son titre
    public FilmDTO findFilmByTitle(String title) {
        var film = filmRepository.findByTitle(title)
                .orElseThrow(() -> new FilmException("Film with title '" + title + "' not found."));
        return filmMapper.toDTO(film);
    }

    // filtrer les films par année de sortie
    public List<FilmDTO> findFilmsByReleaseYear(int year) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> film.getReleaseYear() != null && film.getReleaseYear() == year)
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    // filtrer les films par note minimale
    public List<FilmDTO> findFilmsByMinimumRating(double minRating) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> film.getRating() != null && film.getRating() >= minRating)
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }
}
