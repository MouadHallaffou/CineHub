package com.cinehub.service;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.exception.CategoryException;
import com.cinehub.mapper.CategoryMapper;
import com.cinehub.model.Category;
import com.cinehub.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO saveCategory(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDTO(saved);
    }

    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(id));
        return categoryMapper.toDTO(category);
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException(id);
        }
        if (!findById(id).getFilms().isEmpty()) {
            throw new RuntimeException("Cannot delete category with associated films.");
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(id));

        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(updated);
    }

}

