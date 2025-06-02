package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
