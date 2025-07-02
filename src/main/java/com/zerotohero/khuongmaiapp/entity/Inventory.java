package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @EmbeddedId
    private InventoryId id;

    @ManyToOne
    @MapsId("productId") // map với InventoryId.productId
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private Timestamp lastUpdated;
}