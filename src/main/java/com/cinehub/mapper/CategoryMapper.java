package com.cinehub.mapper;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Méthodes de conversion
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);

}
