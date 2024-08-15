package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier_balances")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_balance_id")
    Long id;
    @OneToOne
    @JoinColumn(name = "supplier_id", unique = true, nullable = false)
    Supplier supplier;
    @Column(name = "total_debt", nullable = false)
    Double totalDebt = 0.0;
    @Column(name = "total_payments", nullable = false)
    Double totalPayment = 0.0;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
}