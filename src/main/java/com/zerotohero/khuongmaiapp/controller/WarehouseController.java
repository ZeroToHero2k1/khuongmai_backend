package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CustomerRequest;
import com.zerotohero.khuongmaiapp.dto.request.WarehouseRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ApiResponse<List<Warehouse>> searchWarehouses(@RequestParam(defaultValue = "") String kw, @RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<Warehouse>>builder().result(warehouseService.searchWarehouses(kw,pageable).getContent()).build();
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
    public ApiResponse<Void> delete(@PathVariable String id){
        warehouseService.deleteWarehouse(id);
        return ApiResponse.<Void>builder().message("Đã xóa thành công").build();
    }
}
