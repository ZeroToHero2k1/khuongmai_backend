package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "warehouse")
@Data
public class Warehouse {
    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "warehouse_name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "phone")
    private String phone;
}

