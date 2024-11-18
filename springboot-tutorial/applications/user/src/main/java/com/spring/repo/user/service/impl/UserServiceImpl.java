package com.spring.repo.user.service.impl;

import com.spring.repo.common.enums.ErrorMessage;
import com.spring.repo.common.exception.BusinessLogicException;
import com.spring.repo.user.documents.User;
import com.spring.repo.user.documents.subs.Image;
import com.spring.repo.user.dto.BaseResponseDTO;
import com.spring.repo.user.dto.ImageDTO;
import com.spring.repo.user.dto.UserDTO;
import com.spring.repo.user.dto.request.*;
import com.spring.repo.user.enums.ExternalTriggerType;
import com.spring.repo.user.enums.UserRole;
import com.spring.repo.user.mapper.UserMapper;
import com.spring.repo.user.predicate.UserPredicateBuilder;
import com.spring.repo.user.repository.UserRepository;
import com.spring.repo.user.service.ExternalSystemTriggerService;
import com.spring.repo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExternalSystemTriggerService externalSystemTriggerService;


    @Override
    public List<UserDTO> getAllUser() {
        return this.userMapper.toDTOs(userRepository.findAll());
    }

    @Override
    public Page<UserDTO> getPaged(String search, Pageable pageable) {
        Page<User> users = this.userRepository.findAll(UserPredicateBuilder.getPaged(search), pageable);
        return users.map(this.userMapper::toDTO);
    }


    @Override
    public UserDTO byId(String id) throws BusinessLogicException {
        User user = getById(id);
        return this.userMapper.toDTO(userRepository.getPreviewedDocument(user));
    }

    private User getById(String id) throws BusinessLogicException {
        return this.userRepository.findById(id).orElseThrow(() -> BusinessLogicException.builder().errorMessage(ErrorMessage.RESOURCE_NOT_FOUND).params(List.of(id)).build());
    }


    @Override
    public BaseResponseDTO createUser(CreateUserDTO payloadDTO) throws BusinessLogicException {
        if (this.userRepository.existsByEmail(payloadDTO.getEmail())) {
            throw BusinessLogicException
                    .builder()
                    .errorMessage(ErrorMessage.EMAIL_EXISTED)
                    .params(List.of(payloadDTO.getEmail()))
                    .build();
        }
        User user = new User();
        this.userMapper.create(payloadDTO, user);
        user.setRole(UserRole.USER);
        this.userRepository.saveDraftOrEditing(user);
        return this.userMapper.toBaseDTO(user);
    }


    @Override
    public BaseResponseDTO updateUserById(UpdateUserDTO payloadDTO) throws BusinessLogicException {
        User user = userRepository.partialUpdate(payloadDTO.getId(), userItem -> {
            userRepository.findOne(UserPredicateBuilder.existsByEmail(payloadDTO.getEmail())).ifPresent(item -> {
                if (!item.getId().equals(payloadDTO.getId())) {
                    throw BusinessLogicException.builder()
                            .errorMessage(ErrorMessage.EMAIL_EXISTED)
                            .params(List.of(payloadDTO.getEmail()))
                            .build();
                }
            });
            this.userMapper.updateUserById(payloadDTO, userItem);
        });
        return this.userMapper.toBaseDTO(user);
    }


    @Override
    public BaseResponseDTO updateImageUserById(UpdateImageUserDTO payloadDTO) throws BusinessLogicException {
        User user = userRepository.partialUpdate(payloadDTO.getId(), userItem -> {
            Map<String, Image> imageMap = userItem.getImages().stream().collect(Collectors.toMap(Image::getImageName, Function.identity()));
            for (ImageDTO imageDTO : payloadDTO.getImages()) {
                Image updateImage = imageMap.get(imageDTO.getImageName());
                if (updateImage != null) {
                    updateImage.setPathName(imageDTO.getPathName());
                } else {
                    userItem.getImages().add(new Image(imageDTO.getImageName(), imageDTO.getPathName()));
                }
            }
        });
        return this.userMapper.toBaseDTO(user);
    }

    @Override
    public BaseResponseDTO resetPasswordSocialById(UpdateSocialUserDTO payloadDTO) throws BusinessLogicException {
        User user = userRepository.partialUpdate(payloadDTO.getId(), userItem -> {
            userItem.getSocials().stream()
                    .filter(social -> social.getSocialName().equals(payloadDTO.getSocialName()))
                    .findFirst()
                    .ifPresent(social -> social.setPassword(payloadDTO.getPassword()));
        });
        return this.userMapper.toBaseDTO(user);
    }

    @Override
    public void updatePassword(UpdateUserPasswordDTO payloadDTO) throws BusinessLogicException {
        userRepository.partialUpdatePassword(payloadDTO.getEmail(), userItem -> {
            userItem.setPassword(payloadDTO.getPassword());
        });
    }

    @Override
    public void updateRoleUser(UpdateRoleUserDTO payloadDTO) throws BusinessLogicException {
        this.userRepository.partialUpdate(payloadDTO.getId(), userItem -> {
            Optional.ofNullable(payloadDTO.getRole()).ifPresent(role -> {
                if (role.equalsIgnoreCase("admin")) {
                    userItem.setRole(UserRole.ADMIN);
                }
            });
        });
    }


    @Override
    public void publishById(String id) throws BusinessLogicException {
        User user = userRepository.publishDocument(id);
        externalSystemTriggerService.trigger(ExternalTriggerType.SEND_DATA_USER, this.userMapper.triggerSendUser(user));
    }

    @Override
    public void deleteById(String id) throws BusinessLogicException {
        User user = this.getById(id);
        this.userRepository.delete(user);
    }
}
