package com.zerotohero.khuongmaiapp.dto.response;

import com.zerotohero.khuongmaiapp.entity.Department;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Valid
public class EmployeeResponse {
    private String name;
    private String phone;
    private String departmentName;
    private LocalDate dateJoined;
    private Boolean status;
}
