package com.zerotohero.khuongmaiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "warehouse")
@Data
public class Warehouse {
    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "warehouse_name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "fromWarehouse")
    @JsonIgnore
    private List<StockReceiptDetail> exportReceipts; // những phiếu xuất hoặc chuyển đi từ kho này

    @OneToMany(mappedBy = "toWarehouse")
    @JsonIgnore
    private List<StockReceiptDetail> importReceipts; // những phiếu nhập hoặc chuyển đến kho này
}

