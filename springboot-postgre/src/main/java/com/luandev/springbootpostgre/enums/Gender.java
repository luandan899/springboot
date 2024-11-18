package com.luandev.springbootpostgre.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("male")
    MALE,
    @JsonProperty("female")
    FEMALE,
    @JsonProperty("other")
    OTHER;

}
