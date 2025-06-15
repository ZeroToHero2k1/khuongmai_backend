package com.zerotohero.khuongmaiapp.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialRequest {
    private String name;

    private String unit;

    private BigDecimal unitPrice;

    private String supplier;

    private String description;
}
