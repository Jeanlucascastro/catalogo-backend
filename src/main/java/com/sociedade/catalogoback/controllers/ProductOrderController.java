package com.sociedade.catalogoback.controllers;

import com.sociedade.catalogoback.domain.produto.ProductOrder;
import com.sociedade.catalogoback.services.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-orders")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @PostMapping
    public ProductOrder createProductOrder(@RequestBody ProductOrder productOrder) {
        return productOrderService.createProductOrder(productOrder);
    }

    @GetMapping
    public List<ProductOrder> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    @GetMapping("/{id}")
    public ProductOrder getProductOrderById(@PathVariable String id) {
        return productOrderService.getProductOrderById(id);
    }

    @PutMapping("/{id}")
    public ProductOrder updateProductOrder(@PathVariable String id, @RequestBody ProductOrder productOrder) {
        return productOrderService.updateProductOrder(id, productOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteProductOrder(@PathVariable String id) {
        productOrderService.deleteProductOrder(id);
    }
}