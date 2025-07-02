package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
//public class InventoryId implements Serializable {
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "warehouse_id")
//    private Warehouse warehouse;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        InventoryId that = (InventoryId) o;
//        return Objects.equals(product, that.product) &&
//                Objects.equals(warehouse, that.warehouse);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(product, warehouse);
//    }
//}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryId implements Serializable {
    private String productId;
    private String warehouseId;

    // equals() & hashCode() nên override đúng
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryId)) return false;
        InventoryId that = (InventoryId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, warehouseId);
    }
}