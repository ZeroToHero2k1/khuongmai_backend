package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.PermissionRequest;
import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.entity.Permission;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.entity.User;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;

    public Permission createRole(PermissionRequest permissionRequest){
        if(permissionRepository.existsByPermissionName(permissionRequest.getPermissionName())){
            throw new KMAppException(ErrorCode.PERMISSION_EXISTED);
        }
        Permission permission=new Permission();
        permission.setPermissionName(permissionRequest.getPermissionName());
        return permissionRepository.save(permission);
    }

    public Page<Permission> searchPermission(String keyword, Pageable pageable){
        Page<Permission> permissionsPage= permissionRepository.searchPermissions(keyword,pageable);
        return permissionsPage;
    }


    public void deletePermission(String id){
        Permission permission=permissionRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.PERMISSION_NOT_FOUND));
        for(Role role: permission.getRoles()) {
            role.getPermissionSet().remove(permission);
        }
        permissionRepository.delete(permission);
    }
}
