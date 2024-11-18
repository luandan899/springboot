package com.luandev.springbootpostgre.service;

import com.luandev.springbootpostgre.common.response.BaseResponse;
import com.luandev.springbootpostgre.dto.request.UserRequestDTO;
import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import com.luandev.springbootpostgre.dto.response.DetailUserResponseDTO;
import com.luandev.springbootpostgre.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    BaseResponse<DetailUserResponseDTO> getUser(long userId);

    Page<BaseUserResponseDTO> getAllUsers(String search, Pageable pageable);

    Page<BaseUserResponseDTO> getUsers(Pageable pageable, List<String> search, String sortBy, String address);

    Page<?> getUsers(String search, String sort, Pageable pageable);

    BaseResponse createUser(UserRequestDTO request);

    BaseResponse updateUser(long id, UserRequestDTO request);


    BaseResponse changeStatus(long userId, UserStatus status);

    BaseResponse deleteUser(long userId);


}
