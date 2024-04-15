package com.aziz.ecommercerestapi.service;

import com.aziz.ecommercerestapi.entity.Category;

import java.util.List;

public interface CategoryService {

  List<Category> getAllCategories();
  Category getCategoryById(Long catId);
  Category createCategory(Category newCategory);
  Category updateCategory(Long catId, Category updateCategory);
  void deleteCategory(Long catId);
}
