package com.zerotohero.khuongmaiapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerotohero.khuongmaiapp.dto.request.MaterialRequest;
import com.zerotohero.khuongmaiapp.dto.request.ProductRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.ProductResponse;
import com.zerotohero.khuongmaiapp.entity.Material;
import com.zerotohero.khuongmaiapp.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<Material> create(@RequestPart("material") String materialJson
            , @RequestPart("files") MultipartFile[] files) throws IOException {
        MaterialRequest materialRequest = objectMapper.readValue(materialJson, MaterialRequest.class);
        return ApiResponse.<Material>builder().result(materialService.createMaterial(materialRequest,files)).build();
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<Material> update(@PathVariable String id
            ,@RequestPart("material") String materialJson
            , @RequestPart("files") MultipartFile[] files) throws IOException {
        MaterialRequest materialRequest=objectMapper.readValue(materialJson,MaterialRequest.class);
        return ApiResponse.<Material>builder().result(materialService.updateMaterial(id,materialRequest,files)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) throws IOException {
        materialService.deleteMaterial(id);
        return ApiResponse.<Void>builder().message("Đã xóa sản phẩm thành công").build();
    }

    @GetMapping()
    ApiResponse<List<Material>> search(@RequestParam(defaultValue = "")String keyword, @RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<Material>>builder().result(materialService.searchMaterials(keyword,pageable).getContent()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<Material> searchProductById(@PathVariable String id){
        return ApiResponse.<Material>builder().result(materialService.searchMaterialById(id)).build();
    }
}
