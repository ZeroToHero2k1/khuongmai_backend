package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
    boolean existsByPermissionName(String name);

    @Query("select p from Permission p where :keyword is null or :keyword='' or lower(p.permissionName) like lower(concat('%',:keyword,'%'))")
    Page<Permission> searchPermissions(@Param("keyword") String keyword, Pageable pageable);
}
