package com.example.orderservice.mapper;

import com.example.orderservice.client.service.UserClient;
import com.example.orderservice.dto.OrdersDto;
import com.example.orderservice.module.Orders;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrdersMapper {
    @Autowired
    protected UserClient userClient;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Orders toEntity(OrdersDto dto);

    @Mapping(target = "users", ignore = true)
    public abstract OrdersDto toDto(Orders orders);

    @Mapping(target = "users", expression = ("java(userClient.getUserByOrdersId(orders.getId()).getData())"))
    public abstract OrdersDto toDtoWithUser(Orders orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateOrdersFromDto(OrdersDto dto, @MappingTarget Orders orders);

}