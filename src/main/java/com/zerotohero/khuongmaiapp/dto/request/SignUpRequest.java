package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class SignUpRequest {
    @Size(min = 5,message = "MIN_NAME")
    String name;
    @Pattern(regexp = "^[0-9]{10}$", message = "PHONE_INVALID")
    String phone;

    @Size(min = 5,message = "USERNAME_INVALID")
    String username;
    @Size(min = 5,message = "PASSWORD_INVALID")
    String password;

}
