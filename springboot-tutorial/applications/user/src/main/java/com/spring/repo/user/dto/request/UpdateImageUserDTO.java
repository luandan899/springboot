package com.spring.repo.user.dto.request;

import com.spring.repo.user.dto.ImageDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UpdateImageUserDTO {
    private String id;
    private Set<ImageDTO> images;
}
