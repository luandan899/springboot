package com.luandev.springbootpostgre.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luandev.springbootpostgre.common.response.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseUserResponseDTO extends BaseDTO {
    private Long id;
    private String name;
    private String email;
    private String status;

}
