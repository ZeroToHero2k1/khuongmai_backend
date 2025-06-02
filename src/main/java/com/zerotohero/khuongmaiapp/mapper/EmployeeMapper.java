package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "department",ignore = true)
    Employee toEmployee(EmployeeCURequest request);
    EmployeeResponse toEmployeeResponse(Employee employee);
}
