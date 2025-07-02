package com.zerotohero.khuongmaiapp.dto.response;

import com.zerotohero.khuongmaiapp.dto.request.OrderDetailRequest;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.entity.OrderDetail;
import com.zerotohero.khuongmaiapp.entity.Status;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String customerName;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private BigDecimal totalPrice;
    private List<OrderDetailResponse> items;
}
