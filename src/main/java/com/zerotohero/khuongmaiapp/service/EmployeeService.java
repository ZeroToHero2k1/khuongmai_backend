package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.EmployeeMapper;
import com.zerotohero.khuongmaiapp.repository.DepartmentRepository;
import com.zerotohero.khuongmaiapp.repository.EmployeeRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
}
