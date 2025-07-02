package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.StockReceiptDetailRequest;
import com.zerotohero.khuongmaiapp.dto.request.StockReceiptRequest;
import com.zerotohero.khuongmaiapp.dto.response.StockReceiptDetailResponse;
import com.zerotohero.khuongmaiapp.dto.response.StockReceiptResponse;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.entity.StockReceipt;
import com.zerotohero.khuongmaiapp.entity.StockReceiptDetail;
import com.zerotohero.khuongmaiapp.entity.Warehouse;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.ProductRepository;
import com.zerotohero.khuongmaiapp.repository.StockReceiptRepository;
import com.zerotohero.khuongmaiapp.repository.WarehouseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StockReceiptService {
    StockReceiptRepository stockReceiptRepository;
    ProductRepository productRepository;
    WarehouseRepository warehouseRepository;
    InventoryService inventoryService;

    public StockReceiptResponse importStock(StockReceiptRequest stockReceiptRequest) {
        StockReceipt stockReceipt = new StockReceipt();
        stockReceipt.setType("IMPORT");
        stockReceipt.setNote(stockReceiptRequest.getNote());

        List<StockReceiptDetail> stockReceiptDetailList = new ArrayList<>();
        for (StockReceiptDetailRequest detailRequest : stockReceiptRequest.getDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId()).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Warehouse warehouse = warehouseRepository.findById(detailRequest.getWarehouseId()).orElseThrow(() -> new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));

            StockReceiptDetail detail = new StockReceiptDetail();
            detail.setStockReceipt(stockReceipt);
            detail.setProduct(product);
            //nhập vào kho nào?
            detail.setToWarehouse(warehouse);
            detail.setQuantity(detailRequest.getQuantity());
            //nhập số lượng vào kho
            inventoryService.stockReceiving(detailRequest.getWarehouseId(), detailRequest.getProductId(), detailRequest.getQuantity());

            stockReceiptDetailList.add(detail);
        }

        stockReceipt.setDetails(stockReceiptDetailList);
        stockReceiptRepository.save(stockReceipt);
        return StockReceiptResponse.builder()
                .id(stockReceipt.getId())
                .type(stockReceipt.getType())
                .note(stockReceipt.getNote())
                .receiptDate(stockReceipt.getReceiptDate())
                .createdBy(stockReceipt.getCreatedBy())
                .details(stockReceipt.getDetails().stream().map(stockReceiptDetail ->
                        StockReceiptDetailResponse.builder()
                                .warehouseId(stockReceiptDetail.getToWarehouse().getId())
                                .quantity(stockReceiptDetail.getQuantity())
                                .productId(stockReceiptDetail.getProduct().getId())
                                .productName(stockReceiptDetail.getProduct().getName())
                                .build()
                ).toList())
                .build();
    }

    public StockReceiptResponse exportStock(StockReceiptRequest stockReceiptRequest) {
        StockReceipt stockReceipt = new StockReceipt();
        stockReceipt.setType("EXPORT");
        stockReceipt.setNote(stockReceiptRequest.getNote());

        List<StockReceiptDetail> stockReceiptDetailList = new ArrayList<>();
        for (StockReceiptDetailRequest detailRequest : stockReceiptRequest.getDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId()).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Warehouse warehouse = warehouseRepository.findById(detailRequest.getWarehouseId()).orElseThrow(() -> new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));

            StockReceiptDetail detail = new StockReceiptDetail();
            detail.setStockReceipt(stockReceipt);
            detail.setProduct(product);
            //Xuất từ kho nào?
            detail.setFromWarehouse(warehouse);
            detail.setQuantity(detailRequest.getQuantity());
            //nhập số lượng vào kho
            inventoryService.exportStock(detailRequest.getWarehouseId(), detailRequest.getProductId(), detailRequest.getQuantity());

            stockReceiptDetailList.add(detail);
        }

        stockReceipt.setDetails(stockReceiptDetailList);
        stockReceiptRepository.save(stockReceipt);
        return StockReceiptResponse.builder()
                .id(stockReceipt.getId())
                .type(stockReceipt.getType())
                .note(stockReceipt.getNote())
                .receiptDate(stockReceipt.getReceiptDate())
                .createdBy(stockReceipt.getCreatedBy())
                .details(stockReceipt.getDetails().stream().map(stockReceiptDetail ->
                        StockReceiptDetailResponse.builder()
                                .warehouseId(stockReceiptDetail.getFromWarehouse().getId())
                                .quantity(stockReceiptDetail.getQuantity())
                                .productId(stockReceiptDetail.getProduct().getId())
                                .productName(stockReceiptDetail.getProduct().getName())
                                .build()
                ).toList())
                .build();
    }

    public StockReceiptResponse transferStock(StockReceiptRequest stockReceiptRequest){
        StockReceipt stockReceipt = new StockReceipt();
        stockReceipt.setType("TRANSFER");
        stockReceipt.setNote(stockReceiptRequest.getNote());

        List<StockReceiptDetail> stockReceiptDetailList = new ArrayList<>();
        for (StockReceiptDetailRequest detailRequest : stockReceiptRequest.getDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId()).orElseThrow(() -> new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Warehouse fromWarehouse = warehouseRepository.findById(detailRequest.getSourceWarehouseId()).orElseThrow(() -> new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
            Warehouse toWarehouse = warehouseRepository.findById(detailRequest.getDestinationWarehouseId()).orElseThrow(() -> new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));

            StockReceiptDetail detail = new StockReceiptDetail();
            detail.setStockReceipt(stockReceipt);
            detail.setProduct(product);
            detail.setFromWarehouse(fromWarehouse);
            detail.setToWarehouse(toWarehouse);
            detail.setQuantity(detailRequest.getQuantity());
            //xuất từ kho cũ
            inventoryService.exportStock(detail.getFromWarehouse().getId(), detailRequest.getProductId(), detailRequest.getQuantity());
            //chuyển sang kho mới
            inventoryService.stockReceiving(detail.getToWarehouse().getId(),detailRequest.getProductId(),detailRequest.getQuantity());
            stockReceiptDetailList.add(detail);
        }

        stockReceipt.setDetails(stockReceiptDetailList);
        stockReceiptRepository.save(stockReceipt);
        return StockReceiptResponse.builder()
                .id(stockReceipt.getId())
                .type(stockReceipt.getType())
                .note(stockReceipt.getNote())
                .receiptDate(stockReceipt.getReceiptDate())
                .createdBy(stockReceipt.getCreatedBy())
                .details(stockReceipt.getDetails().stream().map(stockReceiptDetail ->
                        StockReceiptDetailResponse.builder()
                                .sourceWarehouseId(stockReceiptDetail.getFromWarehouse().getId())
                                .destinationWarehouseId(stockReceiptDetail.getToWarehouse().getId())
                                .quantity(stockReceiptDetail.getQuantity())
                                .productId(stockReceiptDetail.getProduct().getId())
                                .productName(stockReceiptDetail.getProduct().getName())
                                .build()
                ).toList())
                .build();
    }
}


