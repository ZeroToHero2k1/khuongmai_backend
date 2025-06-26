package com.zerotohero.khuongmaiapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "invalidated_tokens")
public class InvalidatedToken {

    @Id
    @Column(name = "id", nullable = false, length = 64)
    String id;  // Đây là jti từ JWT, thường là UUID hoặc chuỗi ngắn

    @Column(name = "expiry_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date expiryTime;
}
