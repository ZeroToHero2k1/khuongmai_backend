package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="stock_receipt")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class StockReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="receipt_id")
    private String id;

    @Column(name="receipt_type",nullable = false)
    private String type;

    @UpdateTimestamp
    @Column(name = "receipt_date")
    private Timestamp receiptDate;

    @Column(name="note")
    private String note;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(mappedBy = "stockReceipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockReceiptDetail> details = new ArrayList<>();
}
