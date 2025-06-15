package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.dto.request.StatusRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Status;
import com.zerotohero.khuongmaiapp.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping
    public ApiResponse<Status> createStatus(@Valid @RequestBody StatusRequest request){
        return ApiResponse.<Status>builder().result(statusService.createStatus(request)).build();
    }

    @GetMapping
    public ApiResponse<List<Status>> searchStatus(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0")int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<Status>>builder().result(statusService.searchStatus(name,pageable).getContent()).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Status> updateStatus(@PathVariable String id,@Valid @RequestBody StatusRequest request){
        return ApiResponse.<Status>builder().result(statusService.updateStatus(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStatus(@PathVariable String id){
        statusService.deleteStatus(id);
        return ApiResponse.<Void>builder().message("Đã xóa thành công").build();
    }
}
