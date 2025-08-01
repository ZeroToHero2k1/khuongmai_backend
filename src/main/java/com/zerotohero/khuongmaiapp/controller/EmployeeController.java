package com.zerotohero.khuongmaiapp.controller;

import com.cloudinary.Api;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    ApiResponse<Page<EmployeeResponse>> searchEmployee(
            @RequestParam(defaultValue = "") String fullName
            , @RequestParam(defaultValue= "") String phone
            , @RequestParam(defaultValue= "") String departmentId
            , @RequestParam(defaultValue= "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateJoin
            , @RequestParam(required = false) Boolean status
            , @RequestParam(defaultValue = "0") int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<Page<EmployeeResponse>>builder().result(employeeService.searchEmployeeByName(fullName,phone,departmentId,dateJoin,status,pageable)).build();
    }

    @PutMapping("/{id}")
    ApiResponse<EmployeeResponse> updateEmployee(@PathVariable String id,@Valid @RequestBody EmployeeCURequest request){
        return ApiResponse.<EmployeeResponse>builder().result(employeeService.updatEmployee(id,request)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return ApiResponse.<Void>builder().message("Cho nhân viên nghỉ thành công").build();
    }

    @GetMapping("/{id}")
    ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable String id){
        return ApiResponse.<EmployeeResponse>builder().result(employeeService.getEmployeeById(id)).build();
    }
}
