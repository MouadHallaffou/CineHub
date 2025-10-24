package com.cinehub.controller;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    // Constructor
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    // Get all directors
    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        List<DirectorDTO> directors = directorService.findAllDirectors();
        return ResponseEntity.ok(directors);
    }

    // Create a new director
    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO savedDirector = directorService.saveDirector(directorDTO);
        return ResponseEntity.ok(savedDirector);
    }

    // Get a director by ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(directorService.findDirectorById(id));
    }

    // Delete a director by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.ok().build();
    }

    // Update a director by ID
    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable("id") Long id, @RequestBody DirectorDTO directorDTO) {
        DirectorDTO updated = directorService.updateDirector(id, directorDTO);
        return ResponseEntity.ok(updated);
    }

    // rechercher un réalisateur par son nom
    @GetMapping("/searchByLastName/{lastName}")
    public ResponseEntity<DirectorDTO> getDirectorByLastName(@PathVariable("lastName") String lastName) {
        DirectorDTO directorDTO = directorService.findDirectorByName(lastName);
        return ResponseEntity.ok(directorDTO);
    }

    // display all films of a director
    @GetMapping("/{id}/films")
    public ResponseEntity<java.util.Map<Long, String>> getDirectorWithFilms(@PathVariable("id") Long id) {
        java.util.Map<Long, String> films = directorService.findFilmIdsByDirectorId(id);
        return ResponseEntity.ok(films);
    }

}
