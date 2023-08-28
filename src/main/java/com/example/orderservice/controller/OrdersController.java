package com.example.orderservice.controller;

import com.example.orderservice.dto.OrdersDto;
import com.example.orderservice.dto.ResponseDto;
import com.example.orderservice.module.Orders;
import com.example.orderservice.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/create")
    public ResponseDto<OrdersDto> create(@RequestBody OrdersDto dto){
        return ordersService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<OrdersDto> get(@PathVariable("id") Integer id){
        return ordersService.get(id);
    }

    @GetMapping("/get-with-users/{id}")
    public ResponseDto<OrdersDto> getWithUsers(@PathVariable("id") Integer id){
        return ordersService.getWithUsers(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<OrdersDto> update(@RequestBody OrdersDto dto, @PathVariable(value = "id") Integer id){
        return ordersService.update(dto,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<OrdersDto> delete(@PathVariable("id") Integer id){
        return ordersService.delete(id);
    }

}