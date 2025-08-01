package com.zerotohero.khuongmaiapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zerotohero.khuongmaiapp.entity.StockReceiptDetail;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Valid
public class WarehouseResponse {
    private String id;

    private String name;

    private String location;

    private String managerName;

    private String phone;

    private Boolean existExportReceipts;

    private Boolean existImportReceipts;
}
