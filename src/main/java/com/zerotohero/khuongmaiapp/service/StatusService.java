package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.dto.request.StatusRequest;
import com.zerotohero.khuongmaiapp.entity.*;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.StatusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StatusService {
    StatusRepository statusRepository;

    public Status createStatus(StatusRequest request){
        if(statusRepository.existsByName(request.getName()))
            throw new KMAppException(ErrorCode.STATUS_NAME_EXISTED);
        Status status=new Status();
        status.setName(request.getName());
        return statusRepository.save(status);
    }

    public Status updateStatus(String id,StatusRequest request){
        Status status= statusRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.STATUS_NOT_FOUND));
        status.setName(request.getName());
        return statusRepository.save(status);
    }

    public void deleteStatus(String id){
        Status status= statusRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.STATUS_NOT_FOUND));
        for(Order order:status.getOrderList()){
            order.setStatus(null);
        }
        for(Task task:status.getTaskList()){
            task.setStatus(null);
        }
        statusRepository.delete(status);
    }

    public Page<Status> searchStatus(String keyword, Pageable pageable){
        return statusRepository.searchStatus(keyword,pageable);
    }
}
