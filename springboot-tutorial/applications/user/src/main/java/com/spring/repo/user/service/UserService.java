package com.spring.repo.user.service;

import com.spring.repo.common.exception.BusinessLogicException;
import com.spring.repo.user.dto.BaseResponseDTO;
import com.spring.repo.user.dto.UserDTO;
import com.spring.repo.user.dto.request.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUser();

    Page<UserDTO> getPaged(String search, Pageable pageable) throws BusinessLogicException;

    UserDTO byId(String id) throws BusinessLogicException;

    BaseResponseDTO createUser(CreateUserDTO payloadDTO) throws BusinessLogicException;

    BaseResponseDTO updateUserById(UpdateUserDTO payloadDTO) throws BusinessLogicException;

    BaseResponseDTO updateImageUserById(UpdateImageUserDTO payloadDTO) throws BusinessLogicException;

    BaseResponseDTO resetPasswordSocialById(UpdateSocialUserDTO payloadDTO) throws BusinessLogicException;

    void updatePassword(UpdateUserPasswordDTO payloadDTO) throws BusinessLogicException;

    void updateRoleUser(UpdateRoleUserDTO payloadDTO) throws BusinessLogicException;

    void publishById(String id) throws BusinessLogicException;

    void deleteById(String id) throws BusinessLogicException;

}
