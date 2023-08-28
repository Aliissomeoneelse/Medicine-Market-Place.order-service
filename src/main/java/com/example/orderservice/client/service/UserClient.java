package com.example.orderservice.client.service;

import com.example.orderservice.client.dto.UserDto;
import com.example.orderservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "user-service",path = "/user-service/user")
public interface UserClient {
    @GetMapping(value = "/get-user-by-orders/{id}")
    ResponseDto<Set<UserDto>> getUserByOrdersId(@PathVariable("id") Integer id);

}