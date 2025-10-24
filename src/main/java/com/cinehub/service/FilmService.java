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

    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public FilmDTO saveFilm(FilmDTO dto) {
        var film = filmMapper.toEntity(dto);
        var saved = filmRepository.save(film);
        return filmMapper.toDTO(saved);
    }

    public List<FilmDTO> findAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilmDTO findFilmById(Long id) {
        var film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmException(id));
        return filmMapper.toDTO(film);
    }

    public void deleteFilm(Long id) {
        if (!filmRepository.existsById(id)) {
            throw new FilmException(id);
        }
        filmRepository.deleteById(id);
    }

    public FilmDTO updateFilm(Long id, FilmDTO dto) {
        if (!filmRepository.existsById(id)) {
            throw new FilmException(id);
        }
        var film = filmMapper.toEntity(dto);
        film.setFilmID(id);
        var updated = filmRepository.save(film);
        return filmMapper.toDTO(updated);
    }
}
