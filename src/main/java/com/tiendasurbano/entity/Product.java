package com.tiendasurbano.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 40)
    private String sku;
    @Column(nullable = false, length = 120)
    private String name;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private boolean publishable;
    @Column(nullable = false)
    private int stock;
    @Column(length = 300)
    private String sizes;
    @Column(length = 300)
    private String colors;
    @ManyToOne(optional = false)
    private Category category;

    protected Product() {
    }

    public Product(String sku, String name, String description, BigDecimal price,
                   boolean active, boolean publishable, int stock, String sizes,
                   String colors, Category category) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.publishable = publishable;
        this.stock = stock;
        this.sizes = sizes;
        this.colors = colors;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public boolean isActive() { return active; }
    public boolean isPublishable() { return publishable; }
    public int getStock() { return stock; }
    public String getSizes() { return sizes; }
    public String getColors() { return colors; }
    public Category getCategory() { return category; }
}
