package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;

import java.util.List;
import java.util.Map;

public interface IDirectorService {

    DirectorDTO saveDirector(DirectorDTO dto);

    List<DirectorDTO> findAllDirectors();

    void deleteDirector(Long id);

    DirectorDTO findDirectorById(Long id);

    DirectorDTO updateDirector(Long id, DirectorDTO dto);

    Map<Long, String> findFilmsByDirectorId(Long directorId);

    DirectorDTO findDirectorByName(String name);
}

