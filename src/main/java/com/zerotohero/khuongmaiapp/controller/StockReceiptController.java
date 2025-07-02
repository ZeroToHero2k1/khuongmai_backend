package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.StockReceiptRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.StockReceiptResponse;
import com.zerotohero.khuongmaiapp.service.StockReceiptService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StockReceiptController {
    @Autowired
    private StockReceiptService stockReceiptService;

    @PostMapping("/import")
    ApiResponse<StockReceiptResponse> nhapKho(@Valid @RequestBody StockReceiptRequest request){
        return ApiResponse.<StockReceiptResponse>builder()
                .result(stockReceiptService.importStock(request))
                .build();
    }

    @PostMapping("/export")
    ApiResponse<StockReceiptResponse> xuatKho(@Valid @RequestBody StockReceiptRequest request){
        return ApiResponse.<StockReceiptResponse>builder()
                .result(stockReceiptService.exportStock(request))
                .build();
    }

    @PostMapping("/transfer")
    ApiResponse<StockReceiptResponse> chuyenKho(@Valid @RequestBody StockReceiptRequest request){
        return ApiResponse.<StockReceiptResponse>builder()
                .result(stockReceiptService.transferStock(request))
                .build();
    }
}
