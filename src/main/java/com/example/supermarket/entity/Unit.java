package com.example.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "units")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_template_id", referencedColumnName = "unit_template_id", nullable = false)
    @JsonIgnore
    UnitTemplate unitTemplate;
    @Column(name = "accuracy", nullable = false) // 0, 1, 2, 3, 4, 5 scale values
    Integer accuracy;
    @Column(name = "status")
    Boolean status = true;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
    @Column(name = "deleted_at")
    Timestamp deletedAt;
    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    List<Product> productList;
}