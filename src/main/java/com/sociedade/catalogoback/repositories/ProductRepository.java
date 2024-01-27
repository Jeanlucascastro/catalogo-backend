package com.sociedade.catalogoback.repositories;


import com.sociedade.catalogoback.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
