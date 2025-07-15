package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
public class UpdateMyInfoRequest {
    @Size(min = 5,message = "MIN_NAME")
    String name;
    @Pattern(regexp = "^[0-9]{10}$", message = "PHONE_INVALID")
    String phone;

    String departmentName;

}
