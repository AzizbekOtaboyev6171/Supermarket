package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    Long id;
    @Column(name = "supplier_name", unique = true, nullable = false)
    String name;
    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "supplier_phone_numbers", joinColumns = @JoinColumn(name = "supplier_id"))
    @Column(name = "phone_number")
    private List<String> phoneNumber = new ArrayList<>();
    @Column(name = "contact_person")
    String contactPerson;
    @Column(name = "tax_identification_number", unique = true)
    String tin;
    @Column(name = "address")
    String address;
    @Column(name = "bank_name")
    String bankName;
    @Column(name = "bank_account")
    String bankAccount;
    @Column(name = "bank_code")
    String bankCode;
    @Column(name = "note")
    String note;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
    @Column(name = "deleted_at")
    Timestamp deletedAt;
    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    SupplierBalance supplierBalance;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    List<Attachment> attachmentList;
}