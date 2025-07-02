package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.response.InventoryResponse;
import com.zerotohero.khuongmaiapp.entity.Inventory;
import com.zerotohero.khuongmaiapp.entity.InventoryId;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.InventoryRepository;
import com.zerotohero.khuongmaiapp.repository.ProductRepository;
import com.zerotohero.khuongmaiapp.repository.WarehouseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class InventoryService {
    InventoryRepository inventoryRepository;
    ProductRepository productRepository;
    WarehouseRepository warehouseRepository;
    //phương thức nhập số lượng sp trong kho
    public void stockReceiving(String warehouseId, String productId, int quantity) {
        InventoryId inventoryId = new InventoryId(productId, warehouseId);
        boolean isExisted = inventoryRepository.existsById(inventoryId);
        if (isExisted) {
            Inventory existedInventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            existedInventory.setQuantityInStock(existedInventory.getQuantityInStock() + quantity);
            inventoryRepository.save(existedInventory);
        }
        else {
            Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
            Product product = productRepository.findById(productId).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Inventory inventory = new Inventory();
            inventory.setId(inventoryId);
            inventory.setProduct(product);
            inventory.setWarehouse(warehouse);
            inventory.setQuantityInStock(quantity);
            inventoryRepository.save(inventory);
        }
    }
    //phương thức xuất số lượng sp trong kho
    public void exportStock(String warehouseId,String productId, int quantity){
        InventoryId inventoryId = new InventoryId(productId, warehouseId);
        Inventory existedInventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
        if(existedInventory.getQuantityInStock()<quantity)
            throw new KMAppException(ErrorCode.NOT_ENOUGH);

        existedInventory.setQuantityInStock(existedInventory.getQuantityInStock()-quantity);
        inventoryRepository.save(existedInventory);

    }
    //phương thức tìm các kho có đủ số lượng 1 sản phẩm nào đó để lên đơn
    public List<Warehouse> searchWarehouseEnoughToQuantity(String productId, int quantity){
        Product product=productRepository.findById(productId).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<Inventory> inventoryList=inventoryRepository.findAllByProduct(product).stream().filter(inventory -> inventory.getQuantityInStock()>=quantity).toList();
        if(inventoryList.isEmpty())
            throw new KMAppException(ErrorCode.NOT_ENOUGH);
        List<Warehouse> warehouseList=new ArrayList<>();
        for(Inventory inventory:inventoryList){
            Warehouse warehouse=inventory.getWarehouse();
            warehouseList.add(warehouse);
        }
        return warehouseList;
    }
    //phương thức hiện số lượng tồn của 1 product trong tất cả các kho cộng lại
    public int searchQuantityOfProduct(String productId){
        Product product=productRepository.findById(productId).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<Inventory> inventoryList=inventoryRepository.findAllByProduct(product);
        int sumQuantity=0;
        for(Inventory inventory:inventoryList){
            sumQuantity+=inventory.getQuantityInStock();
        }
        return sumQuantity;
    }
    //phương thức show ra tất cả sản phẩm mà kho đó chứa
    public List<InventoryResponse> showAllProductOfWarehouse(String warehouseId){
        Warehouse warehouse=warehouseRepository.findById(warehouseId).orElseThrow(()->new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
        List<Inventory> inventoryList=inventoryRepository.findAllByWarehouse(warehouse);
        return inventoryList.stream().map(inventory -> InventoryResponse.builder()
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .warehouseId(inventory.getWarehouse().getId())
                .warehouseName(inventory.getWarehouse().getName())
                .quantityInStock(inventory.getQuantityInStock())
                .lastUpdated(inventory.getLastUpdated())
                .build()).toList();
    }

//    public InventoryResponse mapToInventoryResponse(Inventory inventory){
//        return InventoryResponse.builder()
//                .warehouseId(inventory.getWarehouse().getId())
//                .warehouseName(inventory.getWarehouse().getName())
//                .productId(inventory.getProduct().getId())
//                .productName(inventory.getProduct().getName())
//                .quantityInStock(inventory.getQuantityInStock())
//                .lastUpdated(inventory.getLastUpdated())
//                .build();
//    }
}
