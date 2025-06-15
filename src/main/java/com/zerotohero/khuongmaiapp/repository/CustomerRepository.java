package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("""
            SELECT c FROM Customer c WHERE :kw is null or :kw ='' or lower(c.fullName) like lower(concat('%',:kw,'%'))
            """)
    Page<Customer> searchCustomers(@Param("kw") String keyword, Pageable pageable);
}
