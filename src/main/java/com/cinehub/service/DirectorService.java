package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.exception.ResourceNotFoundException;
import com.cinehub.mapper.DirectorMapper;
import com.cinehub.model.Film;
import com.cinehub.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DirectorService implements IDirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public DirectorDTO saveDirector(DirectorDTO dto) {
        if (directorRepository.existsByLastNameIgnoreCase(dto.getLastName())) {
            throw new IllegalArgumentException("A director with the same last name already exists.");
        }
        var director = directorMapper.toEntity(dto);
        var saved = directorRepository.save(director);
        return directorMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectorDTO> findAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Director", id);
        }
        if (!findDirectorById(id).getFilms().isEmpty()) {
            throw new RuntimeException("Cannot delete director with associated films.");
        }
        directorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DirectorDTO findDirectorById(Long id) {
        var director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", id));
        return directorMapper.toDTO(director);
    }

    @Override
    public DirectorDTO updateDirector(Long id, DirectorDTO dto) {
        var existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director", id));
        if (directorRepository.existsByLastNameIgnoreCase(dto.getLastName())
                && !existingDirector.getLastName().equalsIgnoreCase(dto.getLastName())) {
            throw new IllegalArgumentException("A director with the same last name already exists.");
        }
        existingDirector.setFirstName(dto.getFirstName());
        existingDirector.setLastName(dto.getLastName());
        existingDirector.setBirthDate(dto.getBirthDate() != null && !dto.getBirthDate().isBlank() ? LocalDate.parse(dto.getBirthDate()) : null);
        existingDirector.setNationality(dto.getNationality());
        existingDirector.setBiography(dto.getBiography());

        var updated = directorRepository.save(existingDirector);
        return directorMapper.toDTO(updated);
    }

    //  consulter la filmographie complète d'un réalisateur
    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> findFilmsByDirectorId(Long directorId) {
        var director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director", directorId));
        return director.getFilms()
                .stream()
                .collect(Collectors.toMap(Film::getFilmID, Film::getTitle));
    }

    //  rechercher un réalisateur par son nom
    @Override
    @Transactional(readOnly = true)
    public DirectorDTO findDirectorByName(String name) {
        var director = directorRepository.findDirectorByLastNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Director with name " + name + " not found"));
        return directorMapper.toDTO(director);
    }

}
