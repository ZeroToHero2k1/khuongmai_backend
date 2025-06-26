package com.zerotohero.khuongmaiapp.dto.response;

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
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
