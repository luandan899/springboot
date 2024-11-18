package com.luandev.springbootpostgre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressDTO {
    private String apartmentNumber;
    private String floor;
    private String building;
    private String streetNumber;
    private String street;
    private String city;
    private String country;
    private Integer addressType;
}
