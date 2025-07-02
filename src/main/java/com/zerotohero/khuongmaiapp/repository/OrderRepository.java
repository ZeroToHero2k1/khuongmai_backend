package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    @Query("""
            SELECT o FROM Order o JOIN o.customer c WHERE :keyword is null or :keyword='' or lower(c.fullName) like lower(concat('%',:keyword,'%'))
            """)
    Page<Order> searchOrders(@Param("keyword") String kw, Pageable pageable);
}
