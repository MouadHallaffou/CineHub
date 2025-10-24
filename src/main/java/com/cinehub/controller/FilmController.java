package com.cinehub.controller;

import com.cinehub.dto.FilmRequestDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // get all films
    @GetMapping
    public ResponseEntity<List<FilmResponseDTO>> getAllFilms() {
        List<FilmResponseDTO> films = filmService.findAllFilms();
        return ResponseEntity.ok(films);
    }

    // Create a new film
    @PostMapping
    public ResponseEntity<FilmResponseDTO> createFilm(@RequestBody FilmRequestDTO filmDTO) {
        FilmResponseDTO savedFilm = filmService.saveFilm(filmDTO);
        return ResponseEntity.ok(savedFilm);
    }

    // get a film by ID
    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable("id") Long id) {
        FilmResponseDTO filmDTO = filmService.findFilmById(id);
        return ResponseEntity.ok(filmDTO);
    }

    // update a film by ID
    @PutMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> updateFilm(@PathVariable("id") Long id, @RequestBody FilmRequestDTO filmDTO) {
        FilmResponseDTO updatedFilm = filmService.updateFilm(id, filmDTO);
        return ResponseEntity.ok(updatedFilm);
    }

    // rechercher un film par son titre
    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<FilmResponseDTO> getFilmByTitle(@PathVariable("title") String title) {
        FilmResponseDTO filmDTO = filmService.findFilmByTitle(title);
        return ResponseEntity.ok(filmDTO);
    }

    // filtrer les films par année de sortie
    @GetMapping("/filterByReleaseYear/{year}")
    public ResponseEntity<List<FilmResponseDTO>> getFilmsByReleaseYear(@PathVariable("year") Integer year) {
        List<FilmResponseDTO> films = filmService.findFilmsByReleaseYear(year);
        return ResponseEntity.ok(films);
    }

    // filtrer les films par note minimale
    @GetMapping("/filterByMinRating/{minRating}")
    public ResponseEntity<List<FilmResponseDTO>> getFilmsByMinRating(@PathVariable("minRating") Double minRating) {
        List<FilmResponseDTO> films = filmService.findFilmsByMinimumRating(minRating);
        return ResponseEntity.ok(films);
    }

}