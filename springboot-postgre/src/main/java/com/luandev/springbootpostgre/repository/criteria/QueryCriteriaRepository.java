package com.luandev.springbootpostgre.repository.criteria;

import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryCriteriaRepository {
    Page<BaseUserResponseDTO> getUser(Pageable pageable, List<String> search, String sortBy, String address);

    List<BaseUserResponseDTO> getUsers(Pageable pageable, List<QueryCriteria> criteriaList, String sortBy, String address);
}
