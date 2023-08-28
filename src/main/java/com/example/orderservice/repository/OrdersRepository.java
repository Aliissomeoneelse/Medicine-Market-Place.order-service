package com.example.orderservice.repository;

import com.example.orderservice.module.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query(value = "select * from orders where deleted_at is null and id = :id", nativeQuery = true)
    Optional<Orders> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);
}
