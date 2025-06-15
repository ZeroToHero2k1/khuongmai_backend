package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material {
    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "material_name", nullable = false)
    private String name;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "description")
    private String description;

    // Quan hệ 1 material có nhiều ảnh
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialImage> images = new ArrayList<>();
}
