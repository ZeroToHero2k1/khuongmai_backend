package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Material;
import com.zerotohero.khuongmaiapp.entity.MaterialImage;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialImageRepository extends JpaRepository<MaterialImage,String> {
    @Query("SELECT i FROM MaterialImage i WHERE i.material = :material")
    List<MaterialImage> findAllByMaterial(@Param("material") Material material);
}
