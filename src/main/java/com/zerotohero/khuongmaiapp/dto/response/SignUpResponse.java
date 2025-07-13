package com.zerotohero.khuongmaiapp.dto.response;

import com.zerotohero.khuongmaiapp.entity.User;
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
public class SignUpResponse {
    User user;
    String token;
}
