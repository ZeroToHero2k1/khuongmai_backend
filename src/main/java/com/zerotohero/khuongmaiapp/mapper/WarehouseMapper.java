package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.WarehouseRequest;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse toWarehouse(WarehouseRequest request);
    void updateWarehouse(@MappingTarget Warehouse warehouse,WarehouseRequest request);
}
