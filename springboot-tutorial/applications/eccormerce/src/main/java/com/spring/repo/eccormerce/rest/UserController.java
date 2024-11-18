package com.spring.repo.eccormerce.rest;


import com.spring.repo.common.cqrs.command.CommandBus;
import com.spring.repo.common.cqrs.query.QueryBus;
import com.spring.repo.eccormerce.cqrs.user.query.GetAllUserQuery;
import com.spring.repo.eccormerce.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private QueryBus queryBus;

    @Autowired
    private CommandBus commandBus;


    @GetMapping()
    public Page<UserDTO> getAll(
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault() Pageable pageable) throws Exception {
        return queryBus.dispatch(new GetAllUserQuery(search, pageable));
    }
}
