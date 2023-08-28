package com.example.orderservice.service;

import com.example.orderservice.dto.CreditDto;
import com.example.orderservice.dto.ResponseDto;
import com.example.orderservice.mapper.CreditMapper;
import com.example.orderservice.module.Credit;
import com.example.orderservice.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditMapper creditMapper;
    private final CreditRepository creditRepository;

    public ResponseDto<CreditDto> create(CreditDto dto) {
        try {
            return ResponseDto.<CreditDto>builder()
                    .data(this.creditMapper.toDto(this.creditRepository.save(this.creditMapper.toEntity(dto))))
                    .message("Credit successful created!")
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CreditDto>builder()
                    .message("Credit while saving error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CreditDto> get(Integer id) {
        try {
            return creditRepository.findByCreditIdAndDeletedAtIsNull(id)
                    .map(credit ->
                            ResponseDto.<CreditDto>builder()
                                    .message("Ok")
                                    .success(true)
                                    .data(creditMapper.toDto(credit))
                                    .build())
                    .orElse(ResponseDto.<CreditDto>builder()
                            .message("Not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CreditDto>builder()
                    .message("Credit while getting error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CreditDto> update(Integer id, CreditDto dto) {
        try {
            return creditRepository.findByCreditIdAndDeletedAtIsNull(id)
                    .map(credit -> {
                        creditMapper.update(credit, dto);
                        creditRepository.save(credit);
                        return ResponseDto.<CreditDto>builder()
                                .message("Credit successful updated!")
                                .success(true)
                                .data(creditMapper.toDto(credit))
                                .build();
                    }).orElse(ResponseDto.<CreditDto>builder()
                            .message("Not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CreditDto>builder()
                    .message("Credit while updating error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CreditDto> delete(Integer id) {
        try {
            return creditRepository.findByCreditIdAndDeletedAtIsNull(id)
                    .map(credit -> {
                        credit.setDeletedAt(LocalDateTime.now());
                        creditRepository.save(credit);
                        return ResponseDto.<CreditDto>builder()
                                .message("Credit successful deleted!")
                                .success(true)
                                .data(creditMapper.toDto(credit))
                                .build();
                    }).orElse(ResponseDto.<CreditDto>builder()
                            .message("Not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CreditDto>builder()
                    .message("Credit while deleting error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Set<CreditDto>> getCreditByUserId(Integer id) {
        Set<Credit> credits = creditRepository.findAllByUserIdAndDeletedAtIsNull(id);
        if (credits.isEmpty()) {
            return ResponseDto.<Set<CreditDto>>builder()
                    .message("Credit are not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Set<CreditDto>>builder()
                .success(true)
                .message("OK")
                .data(credits.stream().map(creditMapper::toDto).collect(Collectors.toSet()))
                .build();
    }
}
