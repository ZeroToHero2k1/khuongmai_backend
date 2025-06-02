package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.RoleCURequest;
import com.zerotohero.khuongmaiapp.entity.Role;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.RoleRepository;
import com.zerotohero.khuongmaiapp.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

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


    public void deleteRole(String id){
        roleRepository.deleteById(id);
    }
}
