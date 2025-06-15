package com.zerotohero.khuongmaiapp.dto.response;

import com.zerotohero.khuongmaiapp.entity.ProductImage;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;

    private String size;

    private String color;

    private BigDecimal unitPrice;

    private String categoryName;
    private List<ProductImage> productImageList=new ArrayList<>();
}
