package com.zerotohero.khuongmaiapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci",nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;
}
