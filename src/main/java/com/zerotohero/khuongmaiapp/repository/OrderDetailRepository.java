package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
