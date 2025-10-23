package com.cinehub.mapper;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Instance auto-générée
//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Méthodes de conversion
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);

}
