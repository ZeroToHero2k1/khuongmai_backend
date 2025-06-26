package com.zerotohero.khuongmaiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {

    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String permissionId;

    @Column(name = "permission_name", unique = true, nullable = false)
    String permissionName;

    @ManyToMany(mappedBy = "permissionSet")
    @JsonIgnore
    private Set<Role> roles;
}
