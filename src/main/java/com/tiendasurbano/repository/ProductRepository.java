package com.tiendasurbano.repository;

import com.tiendasurbano.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
        select p from Product p
        where p.active = true and p.publishable = true
        and (:query is null or lower(p.name) like lower(concat('%', :query, '%'))
             or lower(p.description) like lower(concat('%', :query, '%')))
        and (:category is null or lower(p.category.name) = lower(:category))
        and (:available is null or (:available = true and p.stock > 0) or (:available = false and p.stock = 0))
        order by p.name
        """)
    List<Product> findPublicCatalog(@Param("query") String query,
                                    @Param("category") String category,
                                    @Param("available") Boolean available);

    List<Product> findByActiveTrueAndPublishableTrue();
}
