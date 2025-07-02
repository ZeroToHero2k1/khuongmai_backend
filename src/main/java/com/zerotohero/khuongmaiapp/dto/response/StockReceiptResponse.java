package com.zerotohero.khuongmaiapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class StockReceiptResponse {

    private String id;

    private String type;

    private Timestamp receiptDate;

    private String note;

    private String createdBy;

    private List<StockReceiptDetailResponse> details;
}