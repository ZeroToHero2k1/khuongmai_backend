package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
public class Inventory {

    @EmbeddedId
    private InventoryId id;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;
}