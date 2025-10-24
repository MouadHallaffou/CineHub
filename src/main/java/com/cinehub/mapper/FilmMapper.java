package com.cinehub.mapper;

import com.cinehub.dto.FilmDTO;
import com.cinehub.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FilmMapper {


    @Mapping(target = "directorFirstName", source = "director.firstName")
    @Mapping(target = "directorLastName", source = "director.lastName")
    @Mapping(target = "birthDate", source = "director.birthDate")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "directorID", source = "director.directorID")
    @Mapping(target = "categoryID", source = "category.categoryID")
    FilmDTO toDTO(Film film);


    @Mapping(source = "directorFirstName", target = "director.firstName")
    @Mapping(source = "directorLastName", target = "director.lastName")
    @Mapping(source = "birthDate", target = "director.birthDate")
    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(source = "directorID", target = "director.directorID")
    @Mapping(source = "categoryID", target = "category.categoryID")
    Film toEntity(FilmDTO dto);
}
