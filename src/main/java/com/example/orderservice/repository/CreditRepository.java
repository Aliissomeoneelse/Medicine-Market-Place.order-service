package com.example.orderservice.repository;

import com.example.orderservice.module.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CreditRepository extends CrudRepository<Credit,Integer> {
    Optional<Credit> findByCreditIdAndDeletedAtIsNull(Integer id);
    Set<Credit>findAllByUserIdAndDeletedAtIsNull(Integer userId);
}
