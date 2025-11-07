package com.cinehub.service;

import com.cinehub.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    CategoryDTO saveCategory(CategoryDTO dto);

    List<CategoryDTO> findAllCategories();

    CategoryDTO findById(Long id);

    void deleteById(Long id);

    CategoryDTO updateCategory(Long id, CategoryDTO dto);

    List<Long> findFilmsByCategoryId(Long id);
}