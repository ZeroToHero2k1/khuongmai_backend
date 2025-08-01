package com.zerotohero.khuongmaiapp.repository;

import com.zerotohero.khuongmaiapp.entity.Department;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    @Query("""
            SELECT e 
            FROM Employee e
            WHERE (:fullName is null or :fullName='' or lower(e.name) like lower(concat('%',:fullName,'%')) )
            and (:phone is null or :phone='' or e.phone like concat('%',:phone,'%'))
            and (:depart is null or e.department = :depart)
            and (:dateJoin is null or e.dateJoined =:dateJoin )
            and (:status is null or e.status = :status)
            """)
    Page<Employee> searchEmployees(@Param("fullName") String name
            , @Param("phone") String phone
            , @Param("depart")Department department
            , @Param("dateJoin")LocalDate dateJoin
            , @Param("status") Boolean status
            , Pageable pageable);

    Optional<Employee> findByUser(User user);
}
