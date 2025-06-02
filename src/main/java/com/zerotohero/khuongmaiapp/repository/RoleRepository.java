package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    boolean existsByRoleName(String roleName);

    @Query("SELECT r FROM Role r WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(r.roleName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Role> searchRoles(@Param("keyword") String keyword, Pageable pageable);
}
