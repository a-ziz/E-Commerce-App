package com.aziz.ecommercerestapi.service;

import com.aziz.ecommercerestapi.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
  List<SubCategory> getSubCategoriesByCatId(Long catId);

  SubCategory getSubCategoryById(Long catId, Long subId);

  SubCategory createSubCategory(Long catId, SubCategory subCategory);

  SubCategory updateSubCategory(Long subId, SubCategory updateSubCategory);

  void deleteSubCategory(Long subCatId);
}
