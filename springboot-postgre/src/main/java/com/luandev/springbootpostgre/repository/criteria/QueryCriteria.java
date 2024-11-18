package com.luandev.springbootpostgre.repository.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryCriteria {
    private String key;
    private String operation;
    private Object value;
}
