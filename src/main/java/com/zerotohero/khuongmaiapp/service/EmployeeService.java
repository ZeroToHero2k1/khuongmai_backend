package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.entity.Department;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.EmployeeMapper;
import com.zerotohero.khuongmaiapp.repository.DepartmentRepository;
import com.zerotohero.khuongmaiapp.repository.EmployeeRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Getter
@Setter
public class EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;
    DepartmentRepository departmentRepository;

    public EmployeeResponse createEmployee(EmployeeCURequest request){
        Employee employee=employeeMapper.toEmployee(request);
        employee.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElseThrow(()->new KMAppException(ErrorCode.DEPARTMENT_IS_NOT_EXISTED)));
        employeeRepository.save(employee);
        EmployeeResponse employeeResponse=employeeMapper.toEmployeeResponse(employee);
        return employeeResponse;
    }

    public Page<EmployeeResponse> searchEmployeeByName(String name, String phone, String departmentId, LocalDate dateJoined,Boolean status, Pageable pageable){
        Page<Employee> employeePage=null;
        if(!Objects.equals(departmentId, "")) {
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new KMAppException(ErrorCode.DEPARTMENT_IS_NOT_EXISTED));
            employeePage=employeeRepository.searchEmployees(name,phone,department,dateJoined,status,pageable);
        }
        else{
            employeePage=employeeRepository.searchEmployees(name,phone,null,dateJoined,status,pageable);
        }
        return employeePage.map(employeeMapper::toEmployeeResponse);
    }

    public EmployeeResponse updatEmployee(String id,EmployeeCURequest request){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employeeMapper.updateEmployee(employee,request);
        Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new KMAppException(ErrorCode.DEPARTMENT_IS_NOT_EXISTED));
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    public void deleteEmployee(String id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employee.setStatus(false);
        employeeRepository.save(employee);
    }

    public EmployeeResponse getEmployeeById(String id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        return employeeMapper.toEmployeeResponse(employee);
    }
}
