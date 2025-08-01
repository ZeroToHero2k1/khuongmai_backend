package com.zerotohero.khuongmaiapp.dto.request;

import com.zerotohero.khuongmaiapp.entity.Department;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Valid
public class EmployeeCURequest {
    @Size(min = 5,message = "Tên phải trên 5 ký tự")
    private String name;
    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải có đúng 10 chữ số")
    private String phone;
    private String departmentId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateJoined;
    @Builder.Default
    private Boolean status=true;
}
