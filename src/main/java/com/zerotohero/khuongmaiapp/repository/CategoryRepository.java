package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    boolean existsByName(String name);

    @Query("select c from Category c where :keyword is null or :keyword='' or lower(c.name) like lower(concat('%',:keyword,'%'))")
    Page<Category> searchCategories(@Param("keyword") String keyword, Pageable pageable);
}
