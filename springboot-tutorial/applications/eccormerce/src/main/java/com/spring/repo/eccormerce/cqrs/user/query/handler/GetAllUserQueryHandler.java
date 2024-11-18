package com.spring.repo.eccormerce.cqrs.user.query.handler;

import com.spring.repo.common.cqrs.query.QueryHandler;
import com.spring.repo.eccormerce.cqrs.user.query.GetAllUserQuery;
import com.spring.repo.eccormerce.dao.entity.User;
import com.spring.repo.eccormerce.dao.repository.UserRepository;
import com.spring.repo.eccormerce.dto.user.UserDTO;
import com.spring.repo.eccormerce.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllUserQueryHandler implements QueryHandler<Page<UserDTO>, GetAllUserQuery> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> handle(GetAllUserQuery query) throws Exception {
        Page<User> users = this.userRepository.findByNameContaining(query.getSearch(), query.getPageable());
        return users.map(userMapper::toDTO);
    }
}
