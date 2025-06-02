package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "material")
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
}
