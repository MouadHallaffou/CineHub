package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.exception.DirectorException;
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
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    public DirectorDTO saveDirector(DirectorDTO dto) {
        var director = directorMapper.toEntity(dto);
        var saved = directorRepository.save(director);
        return directorMapper.toDTO(saved);
    }

    public List<DirectorDTO> findAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteDirector(Long id) {
        if(!directorRepository.existsById(id)) {
            throw new DirectorException(id);
        }
        if(!findDirectorById(id).getFilms().isEmpty()) {
            throw new DirectorException("Cannot delete director with associated films");
        }
        directorRepository.deleteById(id);
    }

    public DirectorDTO findDirectorById(Long id) {
        var director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorException(id));
        return directorMapper.toDTO(director);
    }

    public DirectorDTO updateDirector(Long id, DirectorDTO dto) {
        var existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorException(id));

        existingDirector.setFirstName(dto.getFirstName());
        existingDirector.setLastName(dto.getLastName());
        existingDirector.setBirthDate(dto.getBirthDate() != null && !dto.getBirthDate().isBlank() ? LocalDate.parse(dto.getBirthDate()) : null);
        existingDirector.setNationality(dto.getNationality());
        existingDirector.setBiography(dto.getBiography());

        var updated = directorRepository.save(existingDirector);
        return directorMapper.toDTO(updated);
    }

    //  consulter la filmographie complète d'un réalisateur
    public Map<Long, String> findFilmIdsByDirectorId(Long directorId) {
        var director = directorRepository.findById(directorId)
                .orElseThrow(() -> new DirectorException(directorId));
        return director.getFilms()
                .stream()
                .collect(Collectors.toMap(Film::getFilmID, Film::getTitle));
    }

    //  rechercher un réalisateur par son nom
    public DirectorDTO findDirectorByName(String name) {
        var director = directorRepository.findDirectorByLastNameIgnoreCase(name)
                .orElseThrow(() -> new DirectorException("Director with name " + name + " not found"));
        return directorMapper.toDTO(director);
    }

}
