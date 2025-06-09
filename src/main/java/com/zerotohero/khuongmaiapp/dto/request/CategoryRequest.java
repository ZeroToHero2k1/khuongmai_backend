package com.zerotohero.khuongmaiapp.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @Size(min = 5,message = "Tên danh mục phải trên 5 kí tự")
    String name;
}
