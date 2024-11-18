package com.spring.repo.user.dto;

import com.spring.repo.user.documents.subs.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserDetailDTO extends UserDTO implements IRequest {
    private Set<Image> images;
    private Set<SocialDTO> socials;
}
