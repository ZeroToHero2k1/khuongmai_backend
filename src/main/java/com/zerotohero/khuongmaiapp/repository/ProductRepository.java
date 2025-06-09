package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
