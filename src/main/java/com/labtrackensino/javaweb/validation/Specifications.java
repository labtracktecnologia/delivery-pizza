package com.labtrackensino.javaweb.validation;

import java.util.function.BiFunction;

public final class Specifications {

    private Specifications() {
    }

    public static <T> BasicSpecification<T> basic(BiFunction<T, BasicSpecification, Boolean> function) {
        return basic(null, function);
    }

    public static <T> BasicSpecification<T> basic(String message, BiFunction<T, BasicSpecification, Boolean> function) {
        return basic(null, message, function);
    }

    public static <T> BasicSpecification<T> basic(String property, String message,
												  BiFunction<T, BasicSpecification, Boolean> function) {
        return basic(null, property, message, function);
    }

    public static <T> BasicSpecification<T> basic(String bean, String property, String message,
												  BiFunction<T, BasicSpecification, Boolean> function) {
        return new FunctionSpecification<>(bean, property, message, function);
    }

}