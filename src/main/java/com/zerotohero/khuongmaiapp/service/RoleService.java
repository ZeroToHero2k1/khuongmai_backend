package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.dto.request.RolePermissionRequest;
import com.zerotohero.khuongmaiapp.entity.Permission;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.entity.User;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.PermissionRepository;
import com.zerotohero.khuongmaiapp.repository.RoleRepository;
import com.zerotohero.khuongmaiapp.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    public Role createRole(RoleCURequest roleCURequest){
        if(roleRepository.existsByRoleName(roleCURequest.getRoleName())){
            throw new KMAppException(ErrorCode.ROLE_EXISTED);
        }
        Role role=new Role();
        role.setRoleName(roleCURequest.getRoleName());
        return roleRepository.save(role);
    }

    public Page<Role> searchRole(String keyword,Pageable pageable){
        Page<Role> rolePage= roleRepository.searchRoles(keyword,pageable);
        return rolePage;
    }

    public Role setPermissions(String roleId,RolePermissionRequest request){
        Role role=roleRepository.findById(roleId).orElseThrow(()->new KMAppException(ErrorCode.ROLE_NOT_FOUND));
        role.getPermissionSet().clear();
        for(String permissionId:request.getSetPermissions()) {
            Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new KMAppException(ErrorCode.PERMISSION_NOT_FOUND));
            role.getPermissionSet().add(permission);
        }
        return roleRepository.save(role);
    }

    public void deleteRole(String id){
        Role role=roleRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.ROLE_NOT_FOUND));
        for(User user: role.getUsers()) {
            user.setRole(null);
        }
        for(Permission permission: role.getPermissionSet()){
            permission.getRoles().remove(role);
        }
        roleRepository.delete(role);
    }
}
