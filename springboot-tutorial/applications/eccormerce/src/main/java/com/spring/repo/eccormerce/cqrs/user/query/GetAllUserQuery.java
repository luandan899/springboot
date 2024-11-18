package com.spring.repo.eccormerce.cqrs.user.query;

import com.spring.repo.common.cqrs.query.BaseQuery;
import com.spring.repo.eccormerce.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetAllUserQuery extends BaseQuery<Page<UserDTO>> {
    private String search;
    private Pageable pageable;

}
