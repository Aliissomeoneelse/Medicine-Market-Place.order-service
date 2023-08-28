package com.example.orderservice.module;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ("credit"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer creditId;
    Double amount;
    String payed;
    String pay;
    LocalDateTime issueDate;
    LocalDateTime expireDate;
    String period;
    String medService;
    String title;
    String medicalCenterName;
    String creditStatus;
    Boolean successful;
    Integer userId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
