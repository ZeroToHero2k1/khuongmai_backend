package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    boolean existsByUsername(String username);
    boolean existsByEmployee(Employee e);
    Optional<User> findByUsername(String username);

    @Query("""
    SELECT u FROM User u 
    WHERE 
        (:keyword IS NULL OR :keyword = '' OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND 
        (:roleIdString IS NULL OR :roleIdString = '' OR u.role.roleId = :roleIdString)
    """)
    Page<User> searchUsers(@Param("keyword") String keyword,@Param("roleIdString") String roleId, Pageable pageable);
}
