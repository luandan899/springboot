package com.luandev.springbootpostgre.repository;

import com.luandev.springbootpostgre.model.Address;
import com.luandev.springbootpostgre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
