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
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    @Query("SELECT e FROM Employee e WHERE (:keyword is null or :keyword='' or (lower(e.name) like lower(concat('%',:keyword,'%'))))")
    Page<Employee> searchEmployees(@Param("keyword") String name, Pageable pageable);

    Optional<Employee> findByUser(User user);
}
