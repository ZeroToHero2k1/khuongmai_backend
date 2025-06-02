package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    ApiResponse<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCURequest request){
        return ApiResponse.<EmployeeResponse>builder().result(employeeService.createEmployee(request)).build();
    }
}
