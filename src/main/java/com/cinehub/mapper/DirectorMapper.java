package com.cinehub.mapper;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.model.Director;
import com.cinehub.model.Film;
import com.cinehub.util.DateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DateConverter.class)
public interface DirectorMapper {

    @Mapping(target = "birthDate", source = "birthDate")
    DirectorDTO toDTO(Director director);

    @Mapping(target = "birthDate", source = "birthDate")
    Director toEntity(DirectorDTO dto);

    @Mapping(target = "directorFirstName", source = "director.firstName")
    @Mapping(target = "directorLastName", source = "director.lastName")
    @Mapping(target = "birthDate", source = "director.birthDate")
    @Mapping(target = "categoryName", source = "category.name")
    FilmResponseDTO filmToDTO(Film film);

}
