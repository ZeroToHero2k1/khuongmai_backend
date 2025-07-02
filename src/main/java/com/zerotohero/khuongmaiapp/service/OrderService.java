package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.OrderDetailRequest;
import com.zerotohero.khuongmaiapp.dto.request.OrderRequest;
import com.zerotohero.khuongmaiapp.dto.response.OrderDetailResponse;
import com.zerotohero.khuongmaiapp.dto.response.OrderResponse;
import com.zerotohero.khuongmaiapp.entity.*;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    StatusRepository statusRepository;
    ProductRepository productRepository;
    WarehouseRepository warehouseRepository;
    InventoryService inventoryService;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest){
        Customer customer=customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->new KMAppException(ErrorCode.CUSTOMER_NOT_FOUND));

        Status status=statusRepository.findByName("MỚI TẠO").orElseThrow(()->new KMAppException(ErrorCode.STATUS_NOT_FOUND));

        Order order=new Order();
        order.setStatus(status);
        order.setOrderDate(orderRequest.getOrderDate());
        order.setDeliveryDate(orderRequest.getDeliveryDate());
        order.setCustomer(customer);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        BigDecimal sumPrice=BigDecimal.ZERO;

        for(OrderDetailRequest orderDetailRequest:orderRequest.getOrderDetailRequestList()){
            Product product=productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Warehouse warehouse=warehouseRepository.findById(orderDetailRequest.getWarehouseId()).orElseThrow(()->new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
//            InventoryId inventoryId=new InventoryId(product.getId(), warehouse.getId());
//            Inventory inventory=inventoryRepository.findById(inventoryId).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));

            BigDecimal unitPrice = product.getUnitPrice();
            int quantity = orderDetailRequest.getQuantity();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));

            //tạo mới detail
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setWarehouse(warehouse); //Sẽ có 1 hàm cho chọn kho mà còn tồn Product đó, lưu kho vào đây
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(unitPrice);
            orderDetail.setTotalPrice(totalPrice);

            //trừ kho sản phẩm
//            if(inventory.getQuantityInStock()>=orderDetail.getQuantity()) {
//                inventory.setQuantityInStock(inventory.getQuantityInStock() - orderDetail.getQuantity());
//                inventoryRepository.save(inventory);
//            }
//            else
//                throw new KMAppException(ErrorCode.SOLD_OUT);
            inventoryService.exportStock(warehouse.getId(), product.getId(), orderDetail.getQuantity());

            //Tính tổng giá tiền và thêm vào danh sách chi tiết đơn hàng
            orderDetailList.add(orderDetail);
            sumPrice=sumPrice.add(orderDetail.getTotalPrice());

        }

        order.setOrderDetailList(orderDetailList);
        order.setTotalAmount(sumPrice);

        orderRepository.save(order);

        return mapToOrderResponse(order);
    }

    @Transactional
    public OrderResponse updateOrder(String id,OrderRequest orderRequest){
        //tìm đơn
        Order order=orderRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.ORDER_NOT_FOUND));
        //tìm khách
        Customer customer=customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->new KMAppException(ErrorCode.CUSTOMER_NOT_FOUND));
        //set lại các thông tin cơ bản
        order.setOrderDate(orderRequest.getOrderDate());
        order.setDeliveryDate(orderRequest.getDeliveryDate());
        //set lại khách
        order.setCustomer(customer);
        //set lại số lượng tồn cho kho (số lượng tồn trước khi tạo đơn hàng)
        for(OrderDetail orderDetail:order.getOrderDetailList()){
//            InventoryId oldInventoryId=new InventoryId(orderDetail.getProduct().getId(), orderDetail.getWarehouse().getId());
//            Inventory oldInventory=inventoryRepository.findById(oldInventoryId).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
//            oldInventory.setQuantityInStock(oldInventory.getQuantityInStock()+orderDetail.getQuantity());
//            inventoryRepository.save(oldInventory);
            inventoryService.stockReceiving(orderDetail.getWarehouse().getId(),orderDetail.getProduct().getId(),orderDetail.getQuantity());
        }

        //xóa orderDetail
        order.getOrderDetailList().clear();

        BigDecimal sumPrice=BigDecimal.ZERO;

        for(OrderDetailRequest orderDetailRequest:orderRequest.getOrderDetailRequestList()){
            Product product=productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
            Warehouse warehouse=warehouseRepository.findById(orderDetailRequest.getWarehouseId()).orElseThrow(()->new KMAppException(ErrorCode.WAREHOUSE_NOT_FOUND));
//            InventoryId inventoryId=new InventoryId(product.getId(), warehouse.getId());
//            Inventory inventory=inventoryRepository.findById(inventoryId).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));

            BigDecimal unitPrice = product.getUnitPrice();
            int quantity = orderDetailRequest.getQuantity();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));

            //tạo mới detail
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setWarehouse(warehouse); //Sẽ có 1 hàm cho chọn kho mà còn tồn Product đó, lưu kho vào đây
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(unitPrice);
            orderDetail.setTotalPrice(totalPrice);

            //trừ kho sản phẩm
//            if(inventory.getQuantityInStock()>=orderDetail.getQuantity()) {
//                inventory.setQuantityInStock(inventory.getQuantityInStock() - orderDetail.getQuantity());
//                inventoryRepository.save(inventory);
//            }
//            else
//                throw new KMAppException(ErrorCode.SOLD_OUT);

            inventoryService.exportStock(warehouse.getId(), product.getId(), orderDetail.getQuantity());

            //Tính tổng giá tiền và thêm vào danh sách chi tiết đơn hàng
            order.getOrderDetailList().add(orderDetail);
            sumPrice=sumPrice.add(orderDetail.getTotalPrice());

        }

        order.setTotalAmount(sumPrice);

        orderRepository.save(order);

        return mapToOrderResponse(order);
    }

    public OrderResponse findById(String id){
        Order order=orderRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.ORDER_NOT_FOUND));
        return mapToOrderResponse(order);
    }

    @Transactional
    public void deleteOrder(String id){
        Order order=orderRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.ORDER_NOT_FOUND));
        for(OrderDetail orderDetail:order.getOrderDetailList()){
            inventoryService.stockReceiving(orderDetail.getWarehouse().getId(),orderDetail.getProduct().getId(),orderDetail.getQuantity());
        }
        orderRepository.deleteById(id);
    }

    public Page<OrderResponse> searchOrders(String kw, Pageable pageable){
        Page<Order> orders=orderRepository.searchOrders(kw,pageable);
        return orders.map(order -> mapToOrderResponse(order));
    }

    private OrderResponse mapToOrderResponse(Order order){
        return OrderResponse.builder()
                .orderId(order.getId())
                .customerName(order.getCustomer().getFullName())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .status(order.getStatus().getName())
                .totalPrice(order.getTotalAmount())
                .items(order.getOrderDetailList().stream().map(
                        orderDetail -> OrderDetailResponse.builder()
                                .productName(orderDetail.getProduct().getName())
                                .quantity(orderDetail.getQuantity())
                                .productId(orderDetail.getProduct().getId())
                                .unitPrice(orderDetail.getProduct().getUnitPrice())
                                .totalPrice(orderDetail.getTotalPrice())
                                .build()
                ).toList())
                .build();
    }
}
