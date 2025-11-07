package com.cinehub.service;

import com.cinehub.dto.FilmRequestDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.exception.BusinessException;
import com.cinehub.exception.ResourceNotFoundException;
import com.cinehub.mapper.FilmMapper;
import com.cinehub.repository.FilmRepository;
import com.cinehub.model.Film;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService implements IFilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    // Constructor Injection
    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    // CREATE : reçoit un FilmRequestDTO, retourne un FilmResponseDTO
    @Override
    public FilmResponseDTO saveFilm(FilmRequestDTO dto) {
        if (filmRepository.existsByTitle(dto.getTitle())) {
            throw new BusinessException("A film with the same title already exists.");
        }
        Film film = filmMapper.toEntity(dto);
        Film saved = filmRepository.save(film);
        return filmMapper.toResponse(saved);
    }

    // READ ALL
    @Transactional(readOnly = true)
    @Override
    public List<FilmResponseDTO> findAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ by ID
    @Transactional(readOnly = true)
    @Override
    public FilmResponseDTO findFilmById(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", id));
        return filmMapper.toResponse(film);
    }

    // DELETE
    @Override
    public void deleteFilm(Long id) {
        if (!filmRepository.existsById(id)) {
            throw new ResourceNotFoundException("Film", id);
        }
        filmRepository.deleteById(id);
    }

    // UPDATE : reçoit un FilmRequestDTO, retourne un FilmResponseDTO
    @Override
    public FilmResponseDTO updateFilm(Long id, FilmRequestDTO dto) {
        if (!filmRepository.existsById(id)) {
            throw new ResourceNotFoundException("Film", id);
        }
        if (filmRepository.existsByTitle(dto.getTitle())) {
            Film existingFilm = filmRepository.findByTitle(dto.getTitle()).get();
            if (!existingFilm.getFilmID().equals(id)) {
                throw new BusinessException("A film with the same title already exists.");
            }
        }
        Film film = filmMapper.toEntity(dto);
        film.setFilmID(id);
        Film updated = filmRepository.save(film);
        return filmMapper.toResponse(updated);
    }

    // Rechercher par titre
    @Transactional(readOnly = true)
    @Override
    public FilmResponseDTO findFilmByTitle(String title) {
        Film film = filmRepository.findByTitle(title)
                .orElseThrow(() -> new BusinessException("Film with title '" + title + "' not found."));
        return filmMapper.toResponse(film);
    }

    // Filtrer par année de sortie
    @Transactional(readOnly = true)
    @Override
    public List<FilmResponseDTO> findFilmsByReleaseYear(int year) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> film.getReleaseYear() != null && film.getReleaseYear() == year)
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Filtrer par note minimale
    @Transactional(readOnly = true)
    @Override
    public List<FilmResponseDTO> findFilmsByMinimumRating(double minRating) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> film.getRating() != null && film.getRating() >= minRating)
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Consulter tous les films d'une catégorie donnée
    @Transactional(readOnly = true)
    @Override
    public List<FilmResponseDTO> getFilmsByCategorie(String category) {
        return filmRepository.findFilmByCategory_Name(category)
                .stream()
                .map(filmMapper::toResponse)
                .toList();
    }

}
