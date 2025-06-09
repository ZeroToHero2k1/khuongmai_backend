package com.zerotohero.khuongmaiapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerotohero.khuongmaiapp.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("size")
    private String size;
    @JsonProperty("color")
    private String color;
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;
    @JsonProperty("categoryId")
    private String categoryId;
}
