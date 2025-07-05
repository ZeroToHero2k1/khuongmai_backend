package com.zerotohero.khuongmaiapp.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String orderId;

    private String employeeId;

    private String statusId;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
}
