package com.aziz.ecommercerestapi.service;

import com.aziz.ecommercerestapi.entity.Product;

import java.util.List;

public interface ProductService {
  List<Product> getAllProducts();

  List<Product> getProductsByCatId(Long catId);

  List<Product> getProductsBySubId(Long subId);

  Product createProduct(Long catId, Long subId, Product newProduct);

  Product updateProduct(Product updateProduct);

  void deleteProduct(Long productId);
}
