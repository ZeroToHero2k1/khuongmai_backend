package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,String> {
    @Query("""
            SELECT w FROM Warehouse w WHERE :kw is null or :kw ='' or lower(w.name) like lower(concat('%',:kw,'%'))
            """)
    Page<Warehouse> searchWarehouses(@Param("kw") String kw, Pageable pageable);
}
