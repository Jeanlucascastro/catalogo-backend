package com.sociedade.catalogoback.domain.product;

public record ProductResponseDTO(String id, String name, int price) {
    public ProductResponseDTO {
        // Construtor padrão gerado pelo record do Java
    }

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
    }
}