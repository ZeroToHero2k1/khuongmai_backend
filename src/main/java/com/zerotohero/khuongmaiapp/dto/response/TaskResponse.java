package com.zerotohero.khuongmaiapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskResponse {

    private String customerName;
    private String employeeId;
    private String employeeName;
    private String statusId;
    private String statusName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

}
