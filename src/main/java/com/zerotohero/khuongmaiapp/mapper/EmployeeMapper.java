package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.EmployeeCURequest;
import com.zerotohero.khuongmaiapp.dto.response.EmployeeResponse;
import com.zerotohero.khuongmaiapp.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "department",ignore = true)
    Employee toEmployee(EmployeeCURequest request);
//    EmployeeResponse toEmployeeResponse(Employee employee);
    void updateEmployee(@MappingTarget Employee employee,EmployeeCURequest request);

    default EmployeeResponse toEmployeeResponse(Employee employee){
        if (employee==null) return null;
        return EmployeeResponse.builder()
                .name(employee.getName())
                .phone(employee.getPhone())
                .dateJoined(employee.getDateJoined())
                .status(employee.getStatus())
                .departmentName(employee.getDepartment().getName())
                .build();

    }
}
