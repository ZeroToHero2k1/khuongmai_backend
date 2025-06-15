package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.CustomerRequest;
import com.zerotohero.khuongmaiapp.dto.request.ProductRequest;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "orderList",ignore = true)
    Customer toCustomer(CustomerRequest request);
    @Mapping(target = "orderList",ignore = true)
    void updateCustomer(@MappingTarget Customer customer,CustomerRequest customerRequest);
}
