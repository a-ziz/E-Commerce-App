package com.aziz.ecommercerestapi.repository;

import com.aziz.ecommercerestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
