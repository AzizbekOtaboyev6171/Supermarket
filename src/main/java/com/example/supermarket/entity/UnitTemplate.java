package com.example.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "unit_templates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_template_id")
    Long id;
    @Column(name = "name", unique = true, nullable = false)
    String name;
    @Column(name = "symbol", unique = true, nullable = false)
    String symbol;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "base_unit_id", referencedColumnName = "unit_template_id")
    @JsonIgnore
    UnitTemplate baseUnit;
    @Column(name = "conversion_factor", nullable = false)
    Integer conversionFactor;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
    @Column(name = "deleted_at")
    Timestamp deletedAt;
}