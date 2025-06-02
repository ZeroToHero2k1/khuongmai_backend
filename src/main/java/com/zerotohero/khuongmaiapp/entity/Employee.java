package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "employee_name", nullable = false)
    private String name;

    @Column(name = "employee_phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "date_joined")
    private LocalDate dateJoined;

    @Column(name = "status")
    private Boolean status;
}
