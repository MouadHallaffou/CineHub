package com.cinehub.mapper;

import com.cinehub.dto.FilmRequestDTO;
import com.cinehub.dto.FilmResponseDTO;
import com.cinehub.model.Category;
import com.cinehub.model.Director;
import com.cinehub.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    // Conversion RequestDTO to Entity
    @Mapping(target = "director", source = "directorID", qualifiedByName = "mapDirector")
    @Mapping(target = "category", source = "categoryID", qualifiedByName = "mapCategory")
    Film toEntity(FilmRequestDTO dto);

    // Conversion Entity to  ResponseDTO
    @Mapping(target = "directorFirstName", source = "director.firstName")
    @Mapping(target = "directorLastName", source = "director.lastName")
    @Mapping(target = "birthDate", source = "director.birthDate")
    @Mapping(target = "categoryName", source = "category.name")
    FilmResponseDTO toResponse(Film film);

    // convertir les IDs en objets
    @Named("mapDirector")
    default Director mapDirector(Long id) {
        if (id == null) return null;
        Director d = new Director();
        d.setDirectorID(id);
        return d;
    }

    @Named("mapCategory")
    default Category mapCategory(Long id) {
        if (id == null) return null;
        Category c = new Category();
        c.setCategoryID(id);
        return c;
    }
}
