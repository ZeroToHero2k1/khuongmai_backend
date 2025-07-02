package com.zerotohero.khuongmaiapp.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String customerId;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

//    private BigDecimal totalAmount;

    private List<OrderDetailRequest> orderDetailRequestList;
}
