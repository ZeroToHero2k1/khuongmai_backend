package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    @Query("SELECT i FROM ProductImage i WHERE i.product = :product")
    List<ProductImage> findAllByProduct(@Param("product") Product product);

    @Transactional
    @Modifying
    @Query("delete from ProductImage i where i.product=:product")
    void deleteByProduct(@Param("product") Product product);


}
