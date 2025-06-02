package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productmaterial")
public class ProductMaterial {
    @Id
    @Column(name = "product_material_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "quantity_used")
    private BigDecimal quantityUsed;

    @Column(name = "unit")
    private String unit;
}
