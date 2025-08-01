package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CustomerRequest;
import com.zerotohero.khuongmaiapp.dto.request.WarehouseRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.WarehouseResponse;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    ApiResponse<Warehouse> create(@RequestBody WarehouseRequest request){
        return ApiResponse.<Warehouse>builder().result(warehouseService.createWarehouse(request)).build();
    }

    @GetMapping()
    ApiResponse<Page<WarehouseResponse>> searchWarehouses(@RequestParam(defaultValue = "") String name,
                                                          @RequestParam(defaultValue = "") String managerName,
                                                          @RequestParam(defaultValue = "") String phone,
                                                          @RequestParam(defaultValue = "0")int page
    ){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<Page<WarehouseResponse>>builder().result(warehouseService.searchWarehouses(name,managerName,phone,pageable)).build();
    }

    @GetMapping("/{id}")
    ApiResponse<Warehouse> getWarehouseById(@PathVariable String id){
        return ApiResponse.<Warehouse>builder().result(warehouseService.getWarehouseById(id)).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Warehouse> update(@PathVariable String id, @RequestBody WarehouseRequest request){
        return ApiResponse.<Warehouse>builder().result(warehouseService.updateWarehouse(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteWarehouse(@PathVariable String id){
        warehouseService.deleteWarehouse(id);
        return ApiResponse.<Void>builder().message("Đã xóa thành công").build();
    }
}
