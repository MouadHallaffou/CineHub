package com.cinehub.service;

import com.cinehub.dto.FilmRequestDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.exception.ResourceNotFoundException;
import com.cinehub.mapper.FilmMapper;
import com.cinehub.model.Film;
import com.cinehub.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    @InjectMocks
    private FilmService filmService;

    private Film film;
    private FilmRequestDTO filmRequestDTO;
    private FilmResponseDTO filmResponseDTO;

    @BeforeEach
    void setUp() {
        film = new Film();
        film.setFilmID(1L);
        film.setTitle("Inception");
        film.setReleaseYear(2010);
        film.setRating(9.0);

        filmRequestDTO = new FilmRequestDTO();
        filmRequestDTO.setTitle("Inception");
        filmRequestDTO.setReleaseYear(2010);
        filmRequestDTO.setRating(9.0);

        filmResponseDTO = new FilmResponseDTO();
        filmResponseDTO.setTitle("Inception");
        filmResponseDTO.setReleaseYear(2010);
        filmResponseDTO.setRating(9.0);
    }

    @Test
    void testSaveFilm_ShouldReturnResponseDTO() {
        when(filmMapper.toEntity(filmRequestDTO)).thenReturn(film);
        when(filmRepository.save(film)).thenReturn(film);
        when(filmMapper.toResponse(film)).thenReturn(filmResponseDTO);

        var result = filmService.saveFilm(filmRequestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Inception");
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void testFindFilmById_ShouldReturnResponseDTO() {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmMapper.toResponse(film)).thenReturn(filmResponseDTO);

        var result = filmService.findFilmById(1L);

        assertThat(result.getTitle()).isEqualTo("Inception");
        verify(filmRepository).findById(1L);
    }

    @Test
    void testFindFilmById_ShouldThrowException_WhenNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> filmService.findFilmById(1L));
        verify(filmRepository).findById(1L);
    }

    @Test
    void testFindAllFilms_ShouldReturnListOfResponseDTO() {
        when(filmRepository.findAll()).thenReturn(List.of(film));
        when(filmMapper.toResponse(film)).thenReturn(filmResponseDTO);

        var results = filmService.findAllFilms();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Inception");
        verify(filmRepository).findAll();
    }

    @Test
    void testDeleteFilm_ShouldDelete_WhenExists() {
        when(filmRepository.existsById(1L)).thenReturn(true);

        filmService.deleteFilm(1L);

        verify(filmRepository).deleteById(1L);
    }

    @Test
    void testDeleteFilm_ShouldThrowException_WhenNotExists() {
        when(filmRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> filmService.deleteFilm(1L));
        verify(filmRepository, never()).deleteById(anyLong());
    }
}
