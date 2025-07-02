package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class StockReceiptRequest {

    // IMPORT / EXPORT / TRANSFER
//    @NotBlank
//    private String type;

//    @NotNull
//    private Timestamp receiptDate;

    private String note;

    @NotEmpty
    private List<StockReceiptDetailRequest> details;
}
