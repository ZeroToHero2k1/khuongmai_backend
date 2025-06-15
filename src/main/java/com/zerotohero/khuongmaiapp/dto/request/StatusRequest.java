package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusRequest {
    @Size(min = 5,message = "Tên trạng thái phải trên 5 kí tự")
    private String name;
}
