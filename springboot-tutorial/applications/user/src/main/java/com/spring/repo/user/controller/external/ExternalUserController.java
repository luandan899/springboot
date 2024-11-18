package com.spring.repo.user.controller.external;

import com.spring.repo.common.exception.BusinessLogicException;
import com.spring.repo.user.consts.ProjectConst;
import com.spring.repo.user.dto.UserDTO;
import com.spring.repo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProjectConst.BASE_EXTERNAL_URL)
@AllArgsConstructor
public class ExternalUserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) throws BusinessLogicException {
        return userService.byId(id);
    }
}
