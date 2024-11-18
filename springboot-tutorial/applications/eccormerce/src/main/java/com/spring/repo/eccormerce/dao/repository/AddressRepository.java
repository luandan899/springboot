package com.spring.repo.eccormerce.dao.repository;


import com.spring.repo.eccormerce.dao.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
