package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {

    boolean existsByName(String name);

    @Query("SELECT d FROM Department d WHERE (:keyword IS NULL OR :keyword='' OR LOWER(d.name) LIKE LOWER(CONCAT('%',:keyword,'%')))")
    Page<Department> searchDepartments(@Param("keyword") String keyword,Pageable pageable);
}
