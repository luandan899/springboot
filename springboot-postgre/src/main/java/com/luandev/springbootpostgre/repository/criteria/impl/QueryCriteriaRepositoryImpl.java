package com.luandev.springbootpostgre.repository.criteria.impl;

import com.luandev.springbootpostgre.dto.response.BaseUserResponseDTO;
import com.luandev.springbootpostgre.mapper.UserMapper;
import com.luandev.springbootpostgre.model.User;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteria;
import com.luandev.springbootpostgre.repository.criteria.QueryCriteriaRepository;
import com.luandev.springbootpostgre.predicate.UserPredicateBuilder;
import com.luandev.springbootpostgre.util.AppUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Slf4j
public class QueryCriteriaRepositoryImpl implements QueryCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPredicateBuilder userPredicateBuilder;

    @Override
    public Page<BaseUserResponseDTO> getUser(Pageable pageable, List<String> search, String sortBy, String address) {

        List<QueryCriteria> queryCriteria = addSearchToQueryCriteria(search);

        List<BaseUserResponseDTO> users = this.getUsers(pageable, queryCriteria, sortBy, address);

        Long totalElements = getTotalElements(queryCriteria, address);

        return new PageImpl<>(users, pageable, totalElements);
    }

    @Override
    public List<BaseUserResponseDTO> getUsers(Pageable pageable, List<QueryCriteria> criteriaList, String sortBy, String address) {
        List<User> users = this.queryUser(pageable, criteriaList, sortBy, address);
        return users.stream().map(userMapper::toDTO).toList();
    }

    private List<User> queryUser(Pageable pageable, List<QueryCriteria> criteriaList, String sortBy, String address) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> queryCriteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = queryCriteria.from(User.class);

        Predicate predicate = userPredicateBuilder.buildPredicate(criteriaBuilder, userRoot, criteriaList, address);
        queryCriteria.where(predicate);

        addSortToQueryCriteria(criteriaBuilder, queryCriteria, userRoot, sortBy);

        return entityManager.createQuery(queryCriteria)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private Long getTotalElements(List<QueryCriteria> criteriaList, String address) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> root = countQuery.from(User.class);

        Predicate predicate = userPredicateBuilder.buildPredicate(criteriaBuilder, root, criteriaList, address);
        countQuery.select(criteriaBuilder.count(root)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private List<QueryCriteria> addSearchToQueryCriteria(List<String> search) {
        List<QueryCriteria> queryCriteria = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            Pattern pattern = Pattern.compile(AppUtils.SEARCH_OPERATOR);
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    queryCriteria.add(new QueryCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }
        return queryCriteria;
    }

    private void addSortToQueryCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<User> query, Root<User> root, String sortBy) {
        if (StringUtils.hasText(sortBy)) {
            Pattern pattern = Pattern.compile(AppUtils.SORT_BY);
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                String fieldName = matcher.group(1);
                String direction = matcher.group(3);

                Order order = "asc".equalsIgnoreCase(direction)
                        ? criteriaBuilder.asc(root.get(fieldName))
                        : criteriaBuilder.desc(root.get(fieldName));

                query.orderBy(order);
            }
        }
    }
}
