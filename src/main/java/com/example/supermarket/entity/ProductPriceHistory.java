package com.example.supermarket.entity;

import com.example.supermarket.status.PriceType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_price_histories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_price_history_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    Product product;
    @Column(name = "product_price", nullable = false)
    BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = "price_history_type", nullable = false)
    PriceType priceType;
    @Column(name = "status")
    Boolean active = true;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
}