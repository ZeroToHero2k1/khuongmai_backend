package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.UserCreationRequest;
import com.zerotohero.khuongmaiapp.dto.request.UserUpdateRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.UpdateMyInfoResponse;
import com.zerotohero.khuongmaiapp.dto.response.UserResponse;
import com.zerotohero.khuongmaiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest userCreationRequest){
        return ApiResponse.<UserResponse>builder().result(userService.createUser(userCreationRequest)).build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> searchUsers(@RequestParam(defaultValue = "") String username
            ,@RequestParam(defaultValue = "") String roleId
            ,@RequestParam(defaultValue = "0") int page){
        int limit=10;
        Pageable pageable= PageRequest.of(page,limit);
        return ApiResponse.<List<UserResponse>>builder().result(userService.searchUsers(username,roleId,pageable).getContent()).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder().result(userService.updateUser(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ApiResponse.<Void>builder().message("Đã xóa user thành công").build();
    }

    @GetMapping("/myinfo")
    public ApiResponse<UpdateMyInfoResponse> getMyInfo(){
        return ApiResponse.<UpdateMyInfoResponse>builder().result(userService.getMyInfo()).build();
    }
}
