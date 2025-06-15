package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ApiResponse<Category> createCategory(@RequestBody CategoryRequest request){
        return ApiResponse.<Category>builder().result(categoryService.createCategory(request)).build();
    }

    @GetMapping
    public ApiResponse<List<Category>> searchCategory(@RequestParam(defaultValue = "") String name,@RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<Category>>builder().result(categoryService.searchCategories(name,pageable).getContent()).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> updateCategory(@PathVariable String id,@RequestBody CategoryRequest request){
        return ApiResponse.<Category>builder().result(categoryService.updateCategory(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder().message("Đã xóa thành công").build();
    }
}
