package com.sociedade.catalogoback.repositories;

import com.sociedade.catalogoback.domain.produto.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {
}
