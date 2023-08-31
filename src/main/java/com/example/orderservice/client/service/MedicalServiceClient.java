package com.example.orderservice.client.service;

import com.example.orderservice.client.dto.UserDto;
import com.example.orderservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "medical-service",path = "/medical-service/medical-service")
public interface MedicalServiceClient {
    @GetMapping(value = "/get-medical-service-by-orders/{id}")
    ResponseDto<Set<UserDto>> getMedicalServiceByOrdersId(@PathVariable("id") Integer id);
}