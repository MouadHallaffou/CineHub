package com.cinehub.mapper;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.model.Category;
import com.cinehub.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "films", source = "films")
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO dto);


    @Mapping(target = "directorFirstName", source = "director.firstName")
    @Mapping(target = "directorLastName", source = "director.lastName")
    @Mapping(target = "birthDate", source = "director.birthDate")
    @Mapping(target = "categoryName", source = "category.name")
    FilmResponseDTO filmToDTO(Film film);
}
