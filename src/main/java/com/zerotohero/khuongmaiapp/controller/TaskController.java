package com.zerotohero.khuongmaiapp.controller;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.dto.request.TaskRequest;
import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import com.zerotohero.khuongmaiapp.dto.response.TaskResponse;
import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Task;
import com.zerotohero.khuongmaiapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ApiResponse<TaskResponse> createTask(@RequestBody TaskRequest request){
        return ApiResponse.<TaskResponse>builder().result(taskService.createTask(request)).build();
    }


    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable String id,@RequestBody TaskRequest request){
        return ApiResponse.<TaskResponse>builder().result(taskService.updateTask(id,request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable String id){
        taskService.deleteTask(id);
        return ApiResponse.<Void>builder().message("Đã xóa task thành công").build();
    }

    @GetMapping
    public ApiResponse<List<TaskResponse>> searchTasks(@RequestParam(defaultValue = "0")int page){
        Pageable pageable=PageRequest.of(page,10);
        return ApiResponse.<List<TaskResponse>>builder().result(taskService.searchTasks(pageable).getContent()).build();
    }
}
