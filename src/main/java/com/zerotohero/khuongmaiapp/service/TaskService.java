package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.TaskRequest;
import com.zerotohero.khuongmaiapp.dto.response.TaskResponse;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.entity.Order;
import com.zerotohero.khuongmaiapp.entity.Status;
import com.zerotohero.khuongmaiapp.entity.Task;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.EmployeeRepository;
import com.zerotohero.khuongmaiapp.repository.OrderRepository;
import com.zerotohero.khuongmaiapp.repository.StatusRepository;
import com.zerotohero.khuongmaiapp.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TaskService {
    TaskRepository taskRepository;
    EmployeeRepository employeeRepository;
    OrderRepository orderRepository;
    StatusRepository statusRepository;

    public TaskResponse createTask(TaskRequest request){
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        Order order= orderRepository.findById(request.getOrderId()).orElseThrow(()->new KMAppException(ErrorCode.ORDER_NOT_FOUND));
        Status status=statusRepository.findByName("MỚI TẠO").orElseThrow(()->new KMAppException(ErrorCode.STATUS_NOT_FOUND));

        Task task=new Task();
        task.setEmployee(employee);
        task.setOrder(order);
        task.setStatus(status);
        task.setDescription(request.getDescription());
        task.setStartDate(request.getStartDate());
        task.setEndDate(request.getEndDate());
        return mapToTaskResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(String id,TaskRequest request){
        Task task=taskRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.TASK_NOT_FOUND));
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        Order order= orderRepository.findById(request.getOrderId()).orElseThrow(()->new KMAppException(ErrorCode.ORDER_NOT_FOUND));
        Status status=statusRepository.findById(request.getStatusId()).orElseThrow(()->new KMAppException(ErrorCode.STATUS_NOT_FOUND));

        task.setEmployee(employee);
        task.setOrder(order);
        task.setStatus(status);
        task.setDescription(request.getDescription());
        task.setStartDate(request.getStartDate());
        task.setEndDate(request.getEndDate());
        return mapToTaskResponse(taskRepository.save(task));
    }

    public TaskResponse findTaskById(String id){
        return mapToTaskResponse(taskRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.TASK_NOT_FOUND)));
    }

    public void deleteTask(String id){
        taskRepository.deleteById(id);
    }

    public Page<TaskResponse> searchTasks(Pageable pageable){
        Page<Task> tasks= taskRepository.findAll(pageable);
        return tasks.map(this::mapToTaskResponse);
    }

    public TaskResponse mapToTaskResponse(Task task){
        return TaskResponse.builder()
                .customerName(task.getOrder().getCustomer().getFullName())
                .employeeId(task.getEmployee().getId())
                .employeeName(task.getEmployee().getName())
                .statusId(task.getStatus().getId())
                .statusName(task.getStatus().getName())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .build();
    }
}
