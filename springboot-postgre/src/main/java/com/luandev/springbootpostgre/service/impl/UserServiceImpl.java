package com.luandev.springbootpostgre.service.impl;

import com.luandev.springbootpostgre.common.enums.ErrorMessage;
import com.luandev.springbootpostgre.common.exception.BusinessLogicException;
import com.luandev.springbootpostgre.common.response.BaseResponse;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteriaRepository;
import com.luandev.springbootpostgre.util.MessageCode;
import com.luandev.springbootpostgre.dto.request.UserRequestDTO;
import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import com.luandev.springbootpostgre.dto.response.DetailUserResponseDTO;
import com.luandev.springbootpostgre.enums.UserStatus;
import com.luandev.springbootpostgre.mapper.AddressMapper;
import com.luandev.springbootpostgre.mapper.UserMapper;
import com.luandev.springbootpostgre.model.User;
import com.luandev.springbootpostgre.repository.UserRepository;
import com.luandev.springbootpostgre.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QueryCriteriaRepository queryCriteriaRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    @Override
    public BaseResponse<DetailUserResponseDTO> getUser(long userId) {
        User user = findUserById(userId);
        DetailUserResponseDTO userDetailResponseDTO = this.userMapper.toUserDetailResponseDTO(user);
        return BaseResponse.<DetailUserResponseDTO>builder().data(userDetailResponseDTO).build();
    }

    @Override
    public Page<BaseUserResponseDTO> getAllUsers(String search, Pageable pageable) {
        Page<User> pageUser = userRepository.searchByNameOrEmail(search, pageable);
        return pageUser.map(this.userMapper::toDTO);
    }

    @Override
    public Page<?> getUsers(String search, String sort, Pageable pageable) {
        return userRepository.searchByNameOrEmail(search, pageable);
    }

    @Override
    public Page<BaseUserResponseDTO> getUsers(Pageable pageable, List<String> search, String sortBy, String address) {
        return this.queryCriteriaRepository.getUser(pageable, search, sortBy, address);
    }

    @Override
    public BaseResponse createUser(UserRequestDTO request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(userEmail -> {
            throw BusinessLogicException.builder()
                    .errorMessage(ErrorMessage.EMAIL_EXISTED)
                    .params(List.of(request.getEmail()))
                    .build();
        });

        User user = this.userMapper.toEntity(request);

        Optional.ofNullable(request.getAddresses()).ifPresent(addressDTOS -> {
            user.saveAddresses(this.addressMapper.toEntities(addressDTOS));
        });

        User userAfterSave = userRepository.save(user);
        log.info("User has added successfully, userId={}", userAfterSave.getId());

        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message(MessageCode.CREATE_USER_SUCCESS)
                .data(userAfterSave.getId())
                .build();
    }

    @Override
    public BaseResponse updateUser(long userId, UserRequestDTO request) {
        User user = findUserById(userId);

        userRepository.findByEmail(request.getEmail()).ifPresent(userEmail -> {
            if (!userEmail.getId().equals(userId)) {
                throw BusinessLogicException.builder()
                        .errorMessage(ErrorMessage.EMAIL_EXISTED)
                        .params(List.of(request.getEmail()))
                        .build();
            }
        });

        user = this.userMapper.toEntity(request);

        User userAfterSave = userRepository.save(user);
        log.info("User has updated successfully, userId={}", userId);

        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .message(MessageCode.UPDATE_USER_SUCCESS)
                .data(userAfterSave.getId())
                .build();
    }


    @Override
    public BaseResponse changeStatus(long userId, UserStatus status) {
        try {
            User user = findUserById(userId);
            user.setStatus(status);
            userRepository.save(user);

            return BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message(MessageCode.UPDATE_STATUS_USER_SUCCESS)
                    .data(userId)
                    .build();

        } catch (Exception e) {
            log.error("User has update status fail, userId={}, status= {}", userId, status);
            return BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(MessageCode.UPDATE_STATUS_USER_FAIL)
                    .data(userId)
                    .build();
        }
    }

    @Override
    public BaseResponse deleteUser(long userId) {

        try {
            User user = findUserById(userId);
            userRepository.delete(user);
            log.info("User has deleted successfully, userId={}", userId);
            return BaseResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message(MessageCode.DELETE_USER_SUCCESS)
                    .data(userId)
                    .build();
        } catch (Exception e) {
            log.error("User has deleted fail, userId={}", userId);
            return BaseResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(MessageCode.DELETE_USER_FAIL)
                    .data(userId)
                    .build();
        }
    }


    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.error(" User Id Not found: {} ", userId);
            return BusinessLogicException.builder()
                    .errorMessage(ErrorMessage.RESOURCE_NOT_FOUND)
                    .params(List.of(userId))
                    .build();
        });
    }

}
