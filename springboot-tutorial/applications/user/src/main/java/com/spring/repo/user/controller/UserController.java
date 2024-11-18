package com.spring.repo.user.controller;

import com.spring.repo.common.exception.BusinessLogicException;
import com.spring.repo.common.response.BaseResponse;
import com.spring.repo.common.response.DefaultSuccessResponse;
import com.spring.repo.common.response.builder.BaseResponseBuilder;
import com.spring.repo.user.consts.ProjectConst;
import com.spring.repo.user.dto.BaseResponseDTO;
import com.spring.repo.user.dto.UserDTO;
import com.spring.repo.user.dto.request.*;
import com.spring.repo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectConst.BASE_URL)
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public BaseResponse<UserDTO> getUserById(@PathVariable String id) throws BusinessLogicException {
        return BaseResponseBuilder.ok().body(userService.byId(id));
    }

    @GetMapping()
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/paged")
    public Page<UserDTO> getPage(
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault(sort = "lastModifiedAt", direction = Sort.Direction.ASC) Pageable pageable)
            throws BusinessLogicException {
        return userService.getPaged(search, pageable);
    }

    @PostMapping()
    public BaseResponse<BaseResponseDTO> createUser(@Valid @RequestBody CreateUserDTO payloadDTO) throws BusinessLogicException {
        return BaseResponseBuilder.ok().body(this.userService.createUser(payloadDTO));
    }

    @PutMapping()
    public BaseResponse<BaseResponseDTO> updateById(@Valid @RequestBody UpdateUserDTO payloadDTO) throws BusinessLogicException {
        return BaseResponseBuilder.ok().body(this.userService.updateUserById(payloadDTO));
    }

    @PutMapping("/social")
    public BaseResponse<BaseResponseDTO> resetPasswordSocialById(@Valid @RequestBody UpdateSocialUserDTO payloadDTO) throws BusinessLogicException {
        return BaseResponseBuilder.ok().body(this.userService.resetPasswordSocialById(payloadDTO));
    }

    @PutMapping("/image")
    public BaseResponse<BaseResponseDTO> updateImageById(@Valid @RequestBody UpdateImageUserDTO payloadDTO) throws BusinessLogicException {
        return BaseResponseBuilder.ok().body(this.userService.updateImageUserById(payloadDTO));
    }

    @PutMapping("/password")
    public BaseResponse<DefaultSuccessResponse> updatePassword(@Valid @RequestBody UpdateUserPasswordDTO payloadDTO) throws BusinessLogicException {
        this.userService.updatePassword(payloadDTO);
        return BaseResponseBuilder.ok().body(DefaultSuccessResponse.defaultResponse());
    }

    @PutMapping("/role")
    public BaseResponse<DefaultSuccessResponse> updateRoleUser(@Valid @RequestBody UpdateRoleUserDTO payloadDTO) throws BusinessLogicException {
        this.userService.updateRoleUser(payloadDTO);
        return BaseResponseBuilder.ok().body(DefaultSuccessResponse.defaultResponse());
    }

    @PutMapping("/public/{id}")
    public BaseResponse<DefaultSuccessResponse> publicUserById(@PathVariable String id) throws BusinessLogicException {
        this.userService.publishById(id);
        return BaseResponseBuilder.ok().body(DefaultSuccessResponse.defaultResponse());
    }


    @DeleteMapping("/{id}")
    public BaseResponse<DefaultSuccessResponse> deleteUser(@PathVariable String id) throws BusinessLogicException {
        this.userService.deleteById(id);
        return BaseResponseBuilder.ok().body(DefaultSuccessResponse.defaultResponseDeleted());
    }
}
