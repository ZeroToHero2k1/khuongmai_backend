package com.zerotohero.khuongmaiapp.controller;


import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.dto.request.RolePermissionRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping()
    ApiResponse<Role> createRole(@Valid @RequestBody RoleCURequest roleCURequest){
        return ApiResponse.<Role>builder().result(roleService.createRole(roleCURequest)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteRole(@PathVariable String id){
        roleService.deleteRole(id);
        return ApiResponse.<String>builder().message("đã xóa thành công role").build();
    }

    @GetMapping()
    ApiResponse<List<Role>> showAllRoles(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "")String keyword){
        int limit=10;
        Pageable pageable=PageRequest.of(page,limit);
        Page<Role> rolePage =roleService.searchRole(keyword,pageable);
        return ApiResponse.<List<Role>>builder().result(rolePage.getContent()).build();
    }

    @PutMapping("/{id}")
    ApiResponse<Role> setPermissions(@PathVariable String id,@RequestBody RolePermissionRequest request){
        return ApiResponse.<Role>builder().result(roleService.setPermissions(id,request)).build();
    }
}
