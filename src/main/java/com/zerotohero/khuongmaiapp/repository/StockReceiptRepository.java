package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.StockReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReceiptRepository extends JpaRepository<StockReceipt,String> {
}
