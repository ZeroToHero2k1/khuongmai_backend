package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class WarehouseRequest {
    private String name;
    private String location;
    private String managerName;
    private String phone;
}
