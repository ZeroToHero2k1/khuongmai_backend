package com.zerotohero.khuongmaiapp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockReceiptDetailResponse {

    private String productId;
    private String productName;

    private int quantity;

    private String warehouseId;           // cho IMPORT/EXPORT
    private String sourceWarehouseId;     // cho TRANSFER
    private String destinationWarehouseId;// cho TRANSFER
}