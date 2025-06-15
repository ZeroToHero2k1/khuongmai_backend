package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material,String> {
    @Query("""
            SELECT m FROM Material m WHERE :kw is null or :kw ='' or lower(m.name) like lower(concat('%',:kw,'%'))
            """)
    Page<Material> searchMaterials(@Param("kw")String kw, Pageable pageable);
}
