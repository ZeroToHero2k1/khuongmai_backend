package com.zerotohero.khuongmaiapp.controller;


import com.zerotohero.khuongmaiapp.dto.request.DepartmentRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Department;
import com.zerotohero.khuongmaiapp.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping()
    ApiResponse<Department> createDepartment(@Valid @RequestBody DepartmentRequest request){
        return ApiResponse.<Department>builder().result(departmentService.createDepartment(request)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteDepartment(@PathVariable String id){
        departmentService.deleteDepartment(id);
        return ApiResponse.<Void>builder().message("Xóa department thành công").build();
    }

    @PutMapping("/{id}")
    ApiResponse<Department> updateDepartment(@PathVariable String id,@Valid @RequestBody DepartmentRequest request){
        return ApiResponse.<Department>builder().result(departmentService.updateDepartmentById(id,request)).build();
    }

    @GetMapping()
    ApiResponse<Page<Department>> getDepartments(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<Page<Department>>builder().result(departmentService.getDepartments(keyword,pageable)).build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<Department>> findAll(){
        return ApiResponse.<List<Department>>builder().result(departmentService.findAllDepartment()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<Department> findById(@PathVariable String id){
        return ApiResponse.<Department>builder().result(departmentService.findDepartmentById(id)).build();
    }

}
