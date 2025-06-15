package com.zerotohero.khuongmaiapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerotohero.khuongmaiapp.dto.request.ProductRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.ProductResponse;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<ProductResponse> createProduct(@RequestPart("product") String productJson
            , @RequestPart("files") MultipartFile[] files) throws IOException {
        ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);
        return ApiResponse.<ProductResponse>builder().result(productService.createProduct(productRequest,files)).build();
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<ProductResponse> updateProduct(@PathVariable String id
            ,@RequestPart("product") String productJson
            , @RequestPart("files") MultipartFile[] files) throws IOException {
        ProductRequest productRequest=objectMapper.readValue(productJson,ProductRequest.class);
        return ApiResponse.<ProductResponse>builder().result(productService.updateProduct(id,productRequest,files)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProduct(@PathVariable String id) throws IOException {
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder().message("Đã xóa sản phẩm thành công").build();
    }

    @GetMapping()
    ApiResponse<List<ProductResponse>> searchProducts(@RequestParam(defaultValue = "")String keyword, @RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<ProductResponse>>builder().result(productService.searchProducts(keyword,pageable).getContent()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> searchProductById(@PathVariable String id){
        return ApiResponse.<ProductResponse>builder().result(productService.findProductById(id)).build();
    }

}
