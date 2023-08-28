package com.example.orderservice.controller;

import com.example.orderservice.dto.CreditDto;
import com.example.orderservice.dto.ResponseDto;
import com.example.orderservice.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/create")
    public ResponseDto<CreditDto> create(@Valid @RequestBody CreditDto dto) {
        return this.creditService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<CreditDto> get(@PathVariable(value = "id") Integer id) {
        return this.creditService.get(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<CreditDto> update(@PathVariable(value = "id") Integer id, @RequestBody CreditDto dto) {
        return this.creditService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CreditDto> delete(@PathVariable(value = "id") Integer id) {
        return this.creditService.delete(id);
    }

    @GetMapping(value = "/get-credit-by-user/{id}")
    public ResponseDto<Set<CreditDto>> getUserByOrdersId(@PathVariable("id") Integer id) {
        return creditService.getCreditByUserId(id);
    }
}
