package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Category;
import com.example.inventorymanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        // vérifier doublon sur name
        // categoryRepository.findByName(category.getName())...
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable (ID : " + id + ")"));
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existing = getCategoryById(id);
        existing.setName(updatedCategory.getName());
        existing.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}