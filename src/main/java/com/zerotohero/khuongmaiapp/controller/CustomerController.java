package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.dto.request.CustomerRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    ApiResponse<Customer> createCustomer(@RequestBody CustomerRequest request){
        return ApiResponse.<Customer>builder().result(customerService.createCustomer(request)).build();
    }

    @GetMapping()
    ApiResponse<List<Customer>> searchCustomers(@RequestParam(defaultValue = "") String kw, @RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<Customer>>builder().result(customerService.getCustomers(kw,pageable).getContent()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<Customer> getCustomerById(@PathVariable String id){
        return ApiResponse.<Customer>builder().result(customerService.getCustomerById(id)).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Customer> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest request){
        return ApiResponse.<Customer>builder().result(customerService.updateCustomer(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return ApiResponse.<Void>builder().message("Đã xóa thành công").build();
    }
}
