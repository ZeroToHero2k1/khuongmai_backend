package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.WarehouseRequest;
import com.zerotohero.khuongmaiapp.dto.response.WarehouseResponse;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.WarehouseMapper;
import com.zerotohero.khuongmaiapp.repository.WarehouseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class WarehouseService {
    WarehouseRepository warehouseRepository;
    WarehouseMapper warehouseMapper;

    public Warehouse createWarehouse(WarehouseRequest request){
        Warehouse warehouse=warehouseMapper.toWarehouse(request);
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouseById(String id){
        return warehouseRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
    }

    public Warehouse updateWarehouse(String id,WarehouseRequest request){
        Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
        warehouseMapper.updateWarehouse(warehouse,request);
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(String id){
        warehouseRepository.deleteById(id);
    }

    public Page<WarehouseResponse> searchWarehouses(String name, String managerName, String phone, Pageable pageable){
        Page<Warehouse> warehousePage= warehouseRepository.searchWarehouses(name,managerName,phone,pageable);
        return warehousePage.map(warehouse -> {
            return WarehouseResponse.builder()
                    .id(warehouse.getId())
                    .name(warehouse.getName())
                    .location(warehouse.getLocation())
                    .phone(warehouse.getPhone())
                    .managerName(warehouse.getManagerName())
                    .existExportReceipts(warehouse.getExportReceipts() != null && !warehouse.getExportReceipts().isEmpty())
                    .existImportReceipts(warehouse.getImportReceipts() != null && !warehouse.getImportReceipts().isEmpty())
                    .build();
        });
    }




}
