package com.zerotohero.khuongmaiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "department_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employeeList=new ArrayList<>();
}