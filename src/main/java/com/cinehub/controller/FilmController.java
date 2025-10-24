package com.cinehub.controller;

import com.cinehub.dto.FilmDTO;
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
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        List<FilmDTO> films = filmService.findAllFilms();
        return ResponseEntity.ok(films);
    }

    // Create a new film
    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO) {
        FilmDTO savedFilm = filmService.saveFilm(filmDTO);
        return ResponseEntity.ok(savedFilm);
    }

    // get a film by ID
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable("id") Long id) {
        FilmDTO filmDTO = filmService.findFilmById(id);
        return ResponseEntity.ok(filmDTO);
    }

    // delete a film by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    // update a film by ID
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable("id") Long id, @RequestBody FilmDTO filmDTO) {
        FilmDTO updatedFilm = filmService.updateFilm(id, filmDTO);
        return ResponseEntity.ok(updatedFilm);
    }

    // rechercher un film par son titre
    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<FilmDTO> getFilmByTitle(@PathVariable("title") String title) {
        FilmDTO filmDTO = filmService.findFilmByTitle(title);
        return ResponseEntity.ok(filmDTO);
    }

    // filtrer les films par année de sortie
    @GetMapping("/filterByReleaseYear/{year}")
    public ResponseEntity<List<FilmDTO>> getFilmsByReleaseYear(@PathVariable("year") Integer year) {
        List<FilmDTO> films = filmService.findFilmsByReleaseYear(year);
        return ResponseEntity.ok(films);
    }

    // filtrer les films par note minimale
    @GetMapping("/filterByMinRating/{minRating}")
    public ResponseEntity<List<FilmDTO>> getFilmsByMinRating(@PathVariable("minRating") Double minRating) {
        List<FilmDTO> films = filmService.findFilmsByMinimumRating(minRating);
        return ResponseEntity.ok(films);
    }
}