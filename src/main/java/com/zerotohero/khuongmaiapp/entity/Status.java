package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
@Data
public class Status {
    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "status",fetch = FetchType.LAZY)
    private List<Order> orderList=new ArrayList<>();

    @OneToMany(mappedBy = "status",fetch = FetchType.LAZY)
    private List<Task> taskList=new ArrayList<>();
}
