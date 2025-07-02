package com.zerotohero.khuongmaiapp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryResponse {
    private String productId;
    private String productName;
    private String warehouseId;
    private String warehouseName;
    private int quantityInStock;
    private Timestamp lastUpdated;
}
