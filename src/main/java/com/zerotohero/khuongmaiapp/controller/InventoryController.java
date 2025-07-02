package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.InventoryResponse;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/warehouses")
    ApiResponse<List<Warehouse>> searchWarehouseEnoughToQuantity(@RequestParam String productId, @RequestParam int quantity){
        return ApiResponse.<List<Warehouse>>builder().result(inventoryService.searchWarehouseEnoughToQuantity(productId,quantity)).build();
    }

    @GetMapping("/quantity")
    ApiResponse<Integer> searchQuantityOfProduct(@RequestParam String productId){
        return ApiResponse.<Integer>builder().result(inventoryService.searchQuantityOfProduct(productId)).build();
    }

    @GetMapping("/products")
    ApiResponse<List<InventoryResponse>> showAllProductOfWarehouse(@RequestParam String warehouseId){
        return ApiResponse.<List<InventoryResponse>>builder().result(inventoryService.showAllProductOfWarehouse(warehouseId)).build();
    }
}
