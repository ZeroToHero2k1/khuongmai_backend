package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.CustomerRequest;
import com.zerotohero.khuongmaiapp.entity.Customer;
import com.zerotohero.khuongmaiapp.entity.Order;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.CustomerMapper;
import com.zerotohero.khuongmaiapp.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public Customer createCustomer(CustomerRequest customerRequest){
        Customer customer= customerMapper.toCustomer(customerRequest);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id,CustomerRequest request){
        Customer customer=customerRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.CUSTOMER_NOT_FOUND));
        customerMapper.updateCustomer(customer,request);
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

    public Page<Customer> getCustomers(String kw, Pageable pageable){
        return customerRepository.searchCustomers(kw, pageable);
    }

    public void deleteCustomer(String id){
        Customer customer= customerRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.CUSTOMER_NOT_FOUND));
        for(Order order:customer.getOrderList()){
            order.setCustomer(null);
        }
        customerRepository.delete(customer);
    }



}
