package com.aziz.ecommercerestapi.service.impl;

import com.aziz.ecommercerestapi.entity.Category;
import com.aziz.ecommercerestapi.repository.CategoryRepository;
import com.aziz.ecommercerestapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category getCategoryById(Long catId) {
    // find category by category id
    Category category = categoryRepository
            .findById(catId)
            .orElseThrow(() -> new RuntimeException("resource not found"));
    return category;
  }

  @Override
  public Category createCategory(Category newCategory) {
    return categoryRepository.save(newCategory);
  }

  @Override
  public Category updateCategory(Long catId, Category updateCategory) {
    // find category by id
    Category category = categoryRepository
            .findById(catId)
            .orElseThrow(() -> new RuntimeException("resource not found"));
    // set the updated values
    category.setCategoryName(updateCategory.getCategoryName());
    category.setCategoryDescription(updateCategory.getCategoryDescription());
    category.setCategoryImage(updateCategory.getCategoryImage());
    // save the changes
    return categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(Long catId) {
    // find category by category id
    Category category = categoryRepository
            .findById(catId)
            .orElseThrow(() -> new RuntimeException("resources not found"));
    categoryRepository.deleteById(catId);
  }
}
