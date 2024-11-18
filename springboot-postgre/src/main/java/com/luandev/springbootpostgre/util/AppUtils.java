package com.luandev.springbootpostgre.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class AppUtils {

    public static String EQUAL = ":";
    public static String BIGGER = ">";
    public static String SMALLER = "<";

    public static String SEARCH_OPERATOR = "(\\w+?)(:|<|>)(.*)";
    public static String SORT_BY = "(\\w+?)(:)(desc|asc)";
}
