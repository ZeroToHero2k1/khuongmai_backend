package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.*;


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
}