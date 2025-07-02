package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.OrderRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.OrderResponse;
import com.zerotohero.khuongmaiapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return ApiResponse.<OrderResponse>builder().result(orderService.createOrder(orderRequest)).build();
    }

    @PutMapping("/{id}")
    ApiResponse<OrderResponse> updateOrder(@PathVariable String id,@RequestBody OrderRequest orderRequest){
        return ApiResponse.<OrderResponse>builder().result(orderService.updateOrder(id,orderRequest)).build();
    }

    @GetMapping
    ApiResponse<List<OrderResponse>> getOrders(@RequestParam(defaultValue = "") String kw,@RequestParam(defaultValue = "0")int page){
        Pageable pageable= PageRequest.of(page,10);
        return ApiResponse.<List<OrderResponse>>builder().result(orderService.searchOrders(kw,pageable).getContent()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderResponse> getOrderById(@PathVariable String id){
        return ApiResponse.<OrderResponse>builder().result(orderService.findById(id)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
        return ApiResponse.<Void>builder().message("Xóa đơn hàng thành công").build();
    }

}
