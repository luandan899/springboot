package com.spring.repo.eccormerce.dto.user;

import com.spring.repo.eccormerce.dao.entity.Address;
import com.spring.repo.eccormerce.dao.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private List<OrderItem> orderItemList;
    private Address address;
}
