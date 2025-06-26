package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.repository.OrderDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;


}
