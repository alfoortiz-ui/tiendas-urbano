package com.tiendasurbano.service;

import com.tiendasurbano.dto.*;
import com.tiendasurbano.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogService {
    private final ProductRepository products;

    public CatalogService(ProductRepository products) {
        this.products = products;
    }

    public List<ProductResponse> findPublicProducts(CatalogFilter filter) {
        return products.findPublicCatalog(normalize(filter.query()), normalize(filter.category()),
                        filter.available())
                .stream().map(ProductResponse::from).toList();
    }

    public ProductResponse findPublicProduct(Long id) {
        return products.findById(id)
                .filter(p -> p.isActive() && p.isPublishable())
                .map(ProductResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Producto no disponible."));
    }

    private String normalize(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
