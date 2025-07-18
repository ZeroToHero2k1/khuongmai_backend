package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.UserCreationRequest;
import com.zerotohero.khuongmaiapp.dto.request.UserUpdateRequest;
import com.zerotohero.khuongmaiapp.dto.response.UpdateMyInfoResponse;
import com.zerotohero.khuongmaiapp.dto.response.UserResponse;
import com.zerotohero.khuongmaiapp.entity.Department;
import com.zerotohero.khuongmaiapp.entity.Employee;
import com.zerotohero.khuongmaiapp.entity.User;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.UserMapper;
import com.zerotohero.khuongmaiapp.repository.DepartmentRepository;
import com.zerotohero.khuongmaiapp.repository.EmployeeRepository;
import com.zerotohero.khuongmaiapp.repository.RoleRepository;
import com.zerotohero.khuongmaiapp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
//        if(userRepository.existsByUsername(request.getUsername()))
//            throw new KMAppException(ErrorCode.UNIQUE_USERNAME);
        User user=userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Employee employee=employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        if(userRepository.existsByEmployee(employee))
            throw new KMAppException(ErrorCode.UNIQUE_ACCOUNT);
        user.setEmployee(employee);
        user.setRole(roleRepository.findById(request.getRoleId()).orElseThrow(()->new KMAppException(ErrorCode.ROLE_NOT_FOUND)));

        try {
            user=userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new KMAppException(ErrorCode.UNIQUE_USERNAME);
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public Page<UserResponse> searchUsers(String keyword,String roleId , Pageable pageable){
        Page<User> userPage=userRepository.searchUsers(keyword,roleId,pageable);
        return userPage.map(userMapper::toUserResponse);
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user=userRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        Employee employee=employeeRepository.findById(request.getEmployeeId()).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
//        if(userRepository.existsByEmployee(employee))
//            throw new KMAppException(ErrorCode.UNIQUE_ACCOUNT);
//        user.setEmployee(employee);
        user.setRole(roleRepository.findById(request.getRoleId()).orElseThrow(()->new KMAppException(ErrorCode.ROLE_NOT_FOUND)));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public UpdateMyInfoResponse getMyInfo(){
        var context= SecurityContextHolder.getContext();
        String name=context.getAuthentication().getName();

//        return userMapper.toUserResponse(userRepository.findByUsername(name).orElseThrow(()->new KMAppException(ErrorCode.USER_NOT_FOUND)));

        User user=userRepository.findByUsername(name).orElseThrow(()->new KMAppException(ErrorCode.USER_NOT_FOUND));
        Employee employee=employeeRepository.findByUser(user).orElseThrow(()->new KMAppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        Department department=employee.getDepartment();

        return UpdateMyInfoResponse.builder()
                .user(user)
                .departmentName(department==null?"Chưa chọn trụ sở":department.getName())
                .build();
    }
}
