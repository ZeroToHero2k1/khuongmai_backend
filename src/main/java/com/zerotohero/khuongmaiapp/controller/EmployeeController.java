package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    ApiResponse<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCURequest request){
        return ApiResponse.<EmployeeResponse>builder().result(employeeService.createEmployee(request)).build();
    }

    @GetMapping
    ApiResponse<List<EmployeeResponse>> searchEmployee(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<EmployeeResponse>>builder().result(employeeService.searchEmployeeByName(keyword,pageable).getContent()).build();
    }

    @PutMapping("/{id}")
    ApiResponse<EmployeeResponse> updateEmployee(@PathVariable String id,@Valid @RequestBody EmployeeCURequest request){
        return ApiResponse.<EmployeeResponse>builder().result(employeeService.updatEmployee(id,request)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return ApiResponse.<Void>builder().message("Xóa nhân viên thành công").build();
    }
}
