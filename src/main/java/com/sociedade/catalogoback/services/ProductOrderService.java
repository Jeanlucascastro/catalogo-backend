package com.sociedade.catalogoback.services;

import com.sociedade.catalogoback.domain.produto.ProductOrder;
import com.sociedade.catalogoback.repositories.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public ProductOrder createProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    public List<ProductOrder> getAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public ProductOrder getProductOrderById(String id) {
        return productOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Order not found with id: " + id));
    }

    public ProductOrder updateProductOrder(String id, ProductOrder productOrder) {
        ProductOrder existingProductOrder = getProductOrderById(id);
        existingProductOrder.setCode(productOrder.getCode());
        existingProductOrder.setName(productOrder.getName());
        existingProductOrder.setPrice(productOrder.getPrice());
        existingProductOrder.setDescription(productOrder.getDescription());
        return productOrderRepository.save(existingProductOrder);
    }

    public void deleteProductOrder(String id) {
        ProductOrder productOrder = getProductOrderById(id);
        productOrderRepository.delete(productOrder);
    }
}