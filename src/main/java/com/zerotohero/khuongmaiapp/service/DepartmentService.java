package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.DepartmentRequest;
import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.entity.Department;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DepartmentService {
    DepartmentRepository departmentRepository;

    public Department createDepartment(DepartmentRequest request){
        if(departmentRepository.existsByName(request.getDepartmentName()))
            throw new KMAppException(ErrorCode.DEPARTMENT_EXISTED);
        Department department=new Department();
        department.setName(request.getDepartmentName());
        return departmentRepository.save(department);
    }


    public Department updateDepartmentById(String id,DepartmentRequest request){
        Department department=departmentRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.DEPARTMENT_IS_NOT_EXISTED));
        department.setName(request.getDepartmentName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(String id){
        departmentRepository.deleteById(id);
    }

    public Page<Department> getDepartments(String keyword, Pageable pageable){
        Page<Department> departmentPage=departmentRepository.searchDepartments(keyword,pageable);
        return departmentPage;
    }



}
