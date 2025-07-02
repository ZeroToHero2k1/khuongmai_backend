package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockReceiptDetailRequest {

    @NotBlank
    private String productId;

    @NotNull
    @Min(1)
    private Integer quantity;

    // Chỉ dùng cho IMPORT / EXPORT
    private String warehouseId;

    // Dùng cho TRANSFER
    private String sourceWarehouseId;
    private String destinationWarehouseId;
}