package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.StockReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReceiptDetailRepository extends JpaRepository<StockReceiptDetail,String> {
}
