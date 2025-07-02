package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    @Query("SELECT i FROM Inventory i WHERE i.product = :product")
    List<Inventory> findAllByProduct(@Param("product") Product product);

    @Query("SELECT i FROM Inventory i WHERE i.warehouse = :warehouse")
    List<Inventory> findAllByWarehouse(@Param("warehouse")Warehouse warehouse);
}
