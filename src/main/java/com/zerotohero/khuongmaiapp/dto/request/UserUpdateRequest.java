package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Valid
public class UserUpdateRequest {
    @Size(min = 5,message = "password phải trên 5 ký tự")
    String password;
    String roleId;
}
