package com.zerotohero.khuongmaiapp.dto.response;

import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Valid
public class UserResponse {
    private String username;
    private Employee employee;
    private String roleName;
}
