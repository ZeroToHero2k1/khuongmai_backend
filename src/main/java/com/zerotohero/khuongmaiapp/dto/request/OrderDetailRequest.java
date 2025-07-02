package com.zerotohero.khuongmaiapp.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {

    private String productId;

    private String warehouseId;

    private Integer quantity;

}
