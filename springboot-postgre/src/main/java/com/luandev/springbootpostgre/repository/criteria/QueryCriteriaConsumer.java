package com.luandev.springbootpostgre.repository.criteria;

import com.luandev.springbootpostgre.util.AppUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryCriteriaConsumer implements Consumer<QueryCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root rootEntity;

    /**
     * Predicate = CriteriaBuilder.and(Expression or Predicate)
     * example case ">" ->Predicate = CriteriaBuilder.and(Predicate, CriteriaBuilder.greaterThanOrEqualTo(key, value))
     */
    @Override
    public void accept(QueryCriteria param) {

        if (param.getOperation().equalsIgnoreCase(AppUtils.BIGGER)) {
            predicate = builder.and(
                    predicate,
                    builder.greaterThanOrEqualTo(
                            rootEntity.get(param.getKey()),
                            param.getValue().toString())
            );

        } else if (param.getOperation().equalsIgnoreCase(AppUtils.SMALLER)) {
            predicate = builder.and(
                    predicate,
                    builder.lessThanOrEqualTo(
                            rootEntity.get(param.getKey()),
                            param.getValue().toString()));

        } else if (param.getOperation().equalsIgnoreCase(AppUtils.EQUAL)) {

            if (rootEntity.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(
                        predicate,
                        builder.like(rootEntity.get(param.getKey()), "%" + param.getValue() + "%"));
            } else {
                predicate = builder.and(
                        predicate,
                        builder.equal(rootEntity.get(param.getKey()), param.getValue()));
            }
        }
    }
}
