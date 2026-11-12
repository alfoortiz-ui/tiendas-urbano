package com.tiendasurbano.dto;

import com.tiendasurbano.entity.Product;
import java.math.BigDecimal;

public record ProductResponse(Long id, String sku, String name, String description,
                              BigDecimal price, String category, String sizes,
                              String colors, boolean available) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getSku(), product.getName(),
                product.getDescription(), product.getPrice(), product.getCategory().getName(),
                product.getSizes(), product.getColors(), product.getStock() > 0);
    }
}
