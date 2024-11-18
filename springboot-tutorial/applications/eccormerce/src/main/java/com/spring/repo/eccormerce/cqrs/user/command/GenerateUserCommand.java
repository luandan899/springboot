package com.spring.repo.eccormerce.cqrs.user.command;

import com.spring.repo.common.cqrs.command.BaseCommand;
import com.spring.repo.common.cqrs.event.DomainEvent;
import com.spring.repo.eccormerce.dto.user.UserPayloadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class GenerateUserCommand extends BaseCommand<ResponseEntity, DomainEvent> {

    private final UserPayloadDTO.UserInfoDTO payload;
}

