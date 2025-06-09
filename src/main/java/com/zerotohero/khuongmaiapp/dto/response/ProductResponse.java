package com.zerotohero.khuongmaiapp.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;

    private String size;

    private String color;

    private BigDecimal unitPrice;

    private String categoryName;
}
