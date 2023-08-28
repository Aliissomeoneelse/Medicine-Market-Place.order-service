package com.example.orderservice.service;

import com.example.orderservice.dto.OrdersDto;
import com.example.orderservice.dto.ResponseDto;
import com.example.orderservice.mapper.OrdersMapper;
import com.example.orderservice.module.Orders;
import com.example.orderservice.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;

    public ResponseDto<OrdersDto> create(OrdersDto dto) {
        try {
            Orders orders = ordersMapper.toEntity(dto);
            orders.setCreatedAt(LocalDateTime.now());
            this.ordersRepository.save(orders);
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message("Order successful created!")
                    .data(ordersMapper.toDto(orders))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order while saving error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<OrdersDto> get(Integer id) {
        Optional<Orders> optional = ordersRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<OrdersDto>builder()
                .success(true)
                .message("OK")
                .data(ordersMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<OrdersDto> getWithUsers(Integer id) {
        Optional<Orders> optional = ordersRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<OrdersDto>builder()
                .success(true)
                .message("OK")
                .data(ordersMapper.toDtoWithUser(optional.get()))
                .build();
    }

    public ResponseDto<OrdersDto> update(OrdersDto dto, Integer id) {
        Optional<Orders> optional = ordersRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Orders orders = optional.get();
            ordersMapper.updateOrdersFromDto(dto, optional.get());
            orders.setId(optional.get().getId());
            orders.setUpdatedAt(LocalDateTime.now());
            this.ordersRepository.save(orders);
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message("Order successful updated!")
                    .data(ordersMapper.toDto(orders))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order while updating error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<OrdersDto> delete(Integer id) {
        Optional<Orders> optional = ordersRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<OrdersDto>builder()
                    .message("User is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Orders orders = optional.get();
            orders.setDeletedAt(LocalDateTime.now());
            this.ordersRepository.save(orders);
            return ResponseDto.<OrdersDto>builder()
                    .success(true)
                    .message("Order successful deleted!")
                    .data(ordersMapper.toDto(orders))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<OrdersDto>builder()
                    .message("Order while deleting error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }
}