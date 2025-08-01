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
            SELECT w FROM Warehouse w WHERE (:name is null or :name ='' or lower(w.name) like lower(concat('%',:name,'%')))
            and (:managerName is null or :managerName ='' or lower(w.managerName) like lower(concat('%',:managerName,'%')))
            and (:phone is null or :phone ='' or w.phone like concat('%',:phone,'%'))
            """)
    Page<Warehouse> searchWarehouses(@Param("name") String name,@Param("managerName") String managerName,@Param("phone") String phone, Pageable pageable);
}
