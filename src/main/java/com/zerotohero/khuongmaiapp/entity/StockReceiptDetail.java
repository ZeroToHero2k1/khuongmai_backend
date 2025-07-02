package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock_receipt_detail")
@Data
public class StockReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detail_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "stock_receipt_id", nullable = false)
    private StockReceipt stockReceipt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // CHỈ sử dụng 1 trong 2 field sau tùy vào loại phiếu
    @ManyToOne
    @JoinColumn(name = "from_warehouse_id")
    private Warehouse fromWarehouse; // Dùng cho EXPORT và TRANSFER

    @ManyToOne
    @JoinColumn(name = "to_warehouse_id")
    private Warehouse toWarehouse; // Dùng cho IMPORT và TRANSFER

    @Column(name = "quantity", nullable = false)
    private int quantity;
}