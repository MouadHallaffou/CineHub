package com.cinehub.service;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.mapper.CategoryMapper;
import com.cinehub.model.Category;
import com.cinehub.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDTO(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(updated);
    }

}

