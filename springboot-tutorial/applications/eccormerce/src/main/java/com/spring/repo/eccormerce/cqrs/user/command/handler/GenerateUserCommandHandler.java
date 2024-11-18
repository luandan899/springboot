package com.spring.repo.eccormerce.cqrs.user.command.handler;

import com.spring.repo.common.cqrs.command.CommandHandler;
import com.spring.repo.common.cqrs.command.CommandResult;
import com.spring.repo.common.cqrs.event.DomainEvent;
import com.spring.repo.eccormerce.cqrs.user.command.GenerateUserCommand;
import com.spring.repo.eccormerce.dao.entity.User;
import com.spring.repo.eccormerce.dao.repository.UserRepository;
import com.spring.repo.eccormerce.dto.user.UserPayloadDTO;
import com.spring.repo.eccormerce.mapper.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenerateUserCommandHandler implements CommandHandler<ResponseEntity, DomainEvent, GenerateUserCommand> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public CommandResult<ResponseEntity, DomainEvent> handle(GenerateUserCommand command) throws Exception {
        UserPayloadDTO.UserInfoDTO payload = command.getPayload();

        User user = userRepository.findByBaseId(payload.getUserId()).orElseGet(
                () -> User.builder()
                        .baseId(payload.getUserId())
                        .name(payload.getName())
                        .email(payload.getEmail())
                        .password(payload.getPassword())
                        .role(this.userMapper.convertRoleUser(payload.getRole())).build());

        this.userMapper.generateUser(payload);
        userRepository.save(user);
        return CommandResult.empty();
    }
}
