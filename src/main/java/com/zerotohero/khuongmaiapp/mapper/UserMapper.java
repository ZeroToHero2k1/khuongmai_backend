package com.zerotohero.khuongmaiapp.mapper;

import com.zerotohero.khuongmaiapp.dto.request.UserCreationRequest;
import com.zerotohero.khuongmaiapp.dto.request.UserUpdateRequest;
import com.zerotohero.khuongmaiapp.dto.response.UserResponse;
import com.zerotohero.khuongmaiapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "employee",ignore = true)
    User toUser(UserCreationRequest request);
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "employee",ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    default UserResponse toUserResponse(User user){
        if(user==null) return null;
        return UserResponse.builder()
                .username(user.getUsername())
                .employee(user.getEmployee())
                .roleName(user.getRole().getRoleName())
                .build();
    }
}
