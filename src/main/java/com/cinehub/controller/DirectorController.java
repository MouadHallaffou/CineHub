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

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        List<DirectorDTO> directors = directorService.findAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO savedDirector = directorService.saveDirector(directorDTO);
        return ResponseEntity.ok(savedDirector);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(directorService.findDirectorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable("id") Long id, @RequestBody DirectorDTO directorDTO) {
        DirectorDTO updated = directorService.updateDirector(id, directorDTO);
        return ResponseEntity.ok(updated);
    }
}
