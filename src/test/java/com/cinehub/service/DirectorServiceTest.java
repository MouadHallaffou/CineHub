package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.exception.ResourceNotFoundException;
import com.cinehub.mapper.DirectorMapper;
import com.cinehub.model.Director;
import com.cinehub.model.Film;
import com.cinehub.repository.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {
    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private DirectorMapper directorMapper;

    @InjectMocks
    private DirectorService directorService;

    private Director director;
    private DirectorDTO directorDTO;

    @BeforeEach
    void setUp() {
        director = new Director();
        director.setDirectorID(1L);
        director.setFirstName("Steven");
        director.setLastName("Spielberg");
        director.setFilms(List.of());

        directorDTO = new DirectorDTO();
        directorDTO.setDirectorID(1L);
        directorDTO.setFirstName("Steven");
        directorDTO.setLastName("Spielberg");
        directorDTO.setFilms(List.of());
    }

    @Test
    void testSaveDirector_ShouldReturnDirectorDTO() {
        when(directorRepository.existsByLastNameIgnoreCase(directorDTO.getLastName())).thenReturn(false);
        when(directorMapper.toEntity(directorDTO)).thenReturn(director);
        when(directorRepository.save(any(Director.class))).thenReturn(director);
        when(directorMapper.toDTO(director)).thenReturn(directorDTO);

        var result = directorService.saveDirector(directorDTO);

        assertThat(result).isEqualTo(directorDTO);
        verify(directorRepository, times(1)).existsByLastNameIgnoreCase(directorDTO.getLastName());
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    void testFindDirectorById_ShouldReturnDirectorDTO() {
        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(directorMapper.toDTO(director)).thenReturn(directorDTO);

        var result = directorService.findDirectorById(1L);

        assertThat(result).isEqualTo(directorDTO);
        verify(directorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindDirectorById_ShouldThrowException_WhenNotFound() {
        when(directorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.findDirectorById(999L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(directorRepository, times(1)).findById(999L);
    }

    @Test
    void testDeleteDirectorById_ShouldInvokeDeleteOnce() {
        when(directorRepository.existsById(1L)).thenReturn(true);
        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(directorMapper.toDTO(director)).thenReturn(directorDTO);
        doNothing().when(directorRepository).deleteById(1L);

        directorService.deleteDirector(1L);

        verify(directorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateDirector_ShouldReturnUpdatedDirectorDTO() {
        DirectorDTO updatedDTO = new DirectorDTO();
        updatedDTO.setFirstName("Martin");
        updatedDTO.setLastName("Scorsese");
        updatedDTO.setBirthDate("1942-11-17");

        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));
        when(directorRepository.existsByLastNameIgnoreCase("Scorsese")).thenReturn(false);
        when(directorRepository.save(any(Director.class))).thenReturn(director);
        when(directorMapper.toDTO(director)).thenReturn(updatedDTO);

        var result = directorService.updateDirector(1L, updatedDTO);

        assertThat(result.getLastName()).isEqualTo("Scorsese");
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    void testFindAllDirectors_ShouldReturnListOfDirectorDTOs() {
        when(directorRepository.findAll()).thenReturn(List.of(director));
        when(directorMapper.toDTO(director)).thenReturn(directorDTO);

        var result = directorService.findAllDirectors();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        verify(directorRepository, times(1)).findAll();
    }

    @Test
    void testFindFilmsByDirectorId_ShouldReturnMapOfFilms() {
        Film film1 = new Film();
        film1.setFilmID(1L);
        film1.setTitle("Film 1");

        Film film2 = new Film();
        film2.setFilmID(2L);
        film2.setTitle("Film 2");

        director.setFilms(List.of(film1, film2));

        when(directorRepository.findById(1L)).thenReturn(Optional.of(director));

        var result = directorService.findFilmsByDirectorId(1L);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        verify(directorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindDirectorByName_ShouldReturnDirectorDTO() {
        when(directorRepository.findDirectorByLastNameIgnoreCase("Spielberg")).thenReturn(Optional.of(director));
        when(directorMapper.toDTO(director)).thenReturn(directorDTO);

        var result = directorService.findDirectorByName("Spielberg");

        assertThat(result.getLastName()).isEqualTo("Spielberg");
        verify(directorRepository, times(1)).findDirectorByLastNameIgnoreCase("Spielberg");
    }
}
