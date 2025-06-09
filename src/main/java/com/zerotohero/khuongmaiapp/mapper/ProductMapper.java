package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.ProductRequest;
import com.zerotohero.khuongmaiapp.dto.response.ProductResponse;
import com.zerotohero.khuongmaiapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category",ignore = true)
    Product toProduct(ProductRequest productRequest);
    @Mapping(target = "category",ignore = true)
    void updateProduct(@MappingTarget Product product,ProductRequest productRequest);

    default ProductResponse toProductResponse(Product product){
        if(product==null) return null;
        return ProductResponse.builder()
                .name(product.getName())
                .color(product.getColor())
                .size(product.getSize())
                .unitPrice(product.getUnitPrice())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
