package com.cinehub.service;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.exception.ResourceNotFoundException;
import com.cinehub.mapper.CategoryMapper;
import com.cinehub.model.Category;
import com.cinehub.model.Film;
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

    // Constructor Injection
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

// ajouter une nouvelle catégorie
public CategoryDTO saveCategory(CategoryDTO dto) {
    if (categoryRepository.existsByName(dto.getName())) {
        throw new IllegalArgumentException("Une catégorie avec ce nom existe déjà");
    }
    Category category = categoryMapper.toEntity(dto);
    Category saved = categoryRepository.save(category);
    return categoryMapper.toDTO(saved);
}

    // récupérer toutes les catégories
    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    // récupérer une catégorie par ID
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
        return categoryMapper.toDTO(category);
    }

    // supprimer une catégorie
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", id);
        }
        if (!findById(id).getFilms().isEmpty()) {
            throw new ResourceNotFoundException("Category with associated films cannot be deleted", id);
        }
        categoryRepository.deleteById(id);
    }

    // mettre à jour une catégorie
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
        if(categoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Une catégorie avec ce nom existe déjà");
        }
        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(updated);
    }

    // consulter tous les films d'une catégorie donnée
   public List<Long> findFilmsByCategoryId(Long id) {
       Category category = categoryRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Category", id));
       return category.getFilms()
               .stream()
               .map(Film::getFilmID)
               .collect(Collectors.toList());
   }
}

