package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;
    @Column(name = "product_name", nullable = false)
    String name;
    @Column(name = "article", nullable = false)
    String article;
    @Column(name = "barcode", nullable = false, unique = true)
    String barcode;
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "unit_id", nullable = false)
    Unit unit;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Attachment> attachmentList;
    @Column(name = "flexible_price")
    Boolean flexiblePrice = false;
    @Column(name = "store_amount")
    BigDecimal storeAmount = BigDecimal.ZERO;
    @Column(name = "warehouse_amount")
    BigDecimal warehouseAmount = BigDecimal.ZERO;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<ProductPriceHistory> productPriceHistoryList;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
    @Column(name = "deleted_at")
    Timestamp deletedAt;
}