package com.luandev.springbootpostgre.controller;

import com.luandev.springbootpostgre.common.response.BaseResponse;
import com.luandev.springbootpostgre.dto.request.UserRequestDTO;
import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import com.luandev.springbootpostgre.dto.response.DetailUserResponseDTO;
import com.luandev.springbootpostgre.enums.UserStatus;
import com.luandev.springbootpostgre.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get Detail User", description = "Send a request via this API to get  detail user")
    @GetMapping("/{userId}")
    public BaseResponse<DetailUserResponseDTO> getUsers(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Get page of users", description = "Send a request via this API to get user page")
    @GetMapping()
    public Page<BaseUserResponseDTO> getUsers(
            @RequestParam(name = "search", required = false) String search,
            @PageableDefault(sort = "updatedAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.getAllUsers(search, pageable);
    }

    @Operation(summary = "Advance search query by criteria", description = "Send a request via this API to get user list by pageNo, pageSize and sort by multiple column")
    @GetMapping("/test")
    public Page<BaseUserResponseDTO> advanceSearchWithCriteria(@PageableDefault(sort = "updatedAt", direction = Sort.Direction.ASC) Pageable pageable,
                                                               @RequestParam(required = false) String sort,
                                                               @RequestParam(required = false) String address,
                                                               @RequestParam(defaultValue = "") List<String> search) {
//        http://localhost:8080/api/user/test?search=name:l,email:3&sort=id:desc
        return userService.getUsers(pageable, search, sort, address);
    }

    @Operation(summary = "Create  user", description = "Send a request via this API to create  user")
    @PostMapping()
    public BaseResponse createIUser(@Valid @RequestBody UserRequestDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @Operation(summary = "Create or Update user", description = "Send a request via this API to create or update user")
    @PutMapping("{userId}")
    public BaseResponse save(@PathVariable Long userId, @Valid @RequestBody UserRequestDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @Operation(summary = "Change status of user", description = "Send a request via this API to change status of user")
    @PatchMapping("/{id}")
    public BaseResponse changeUserStatusById(@PathVariable Long id, @Valid @RequestParam(required = false) UserStatus status) {
        return userService.changeStatus(id, status);
    }

    @Operation(summary = "Delete user permanently", description = "Send a request via this API to delete user permanently")
    @DeleteMapping("/{id}")
    public BaseResponse deleteUserById(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
