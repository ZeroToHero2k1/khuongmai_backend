package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "category_name", nullable = false)
    private String name;
}