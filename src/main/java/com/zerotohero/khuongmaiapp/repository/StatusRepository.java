package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,String> {
    boolean existsByName(String name);
    Optional<Status> findByName(String name);

    @Query("select s from Status s where :keyword is null or :keyword='' or lower(s.name) like lower(concat('%',:keyword,'%'))")
    Page<Status> searchStatus(@Param("keyword") String keyword, Pageable pageable);
}
