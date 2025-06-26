package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.PermissionRequest;
import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.entity.Permission;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/permission")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping()
    ApiResponse<Permission> createPermision(@Valid @RequestBody PermissionRequest permissionRequest){
        return ApiResponse.<Permission>builder().result(permissionService.createRole(permissionRequest)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deletePermission(@PathVariable String id){
        permissionService.deletePermission(id);
        return ApiResponse.<String>builder().message("đã xóa thành công permission").build();
    }

    @GetMapping()
    ApiResponse<List<Permission>> showAllRoles(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "")String keyword){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        Page<Permission> permissions =permissionService.searchPermission(keyword,pageable);
        return ApiResponse.<List<Permission>>builder().result(permissions.getContent()).build();
    }
}
