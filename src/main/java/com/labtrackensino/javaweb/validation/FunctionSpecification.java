package com.labtrackensino.javaweb.validation;

import java.util.Objects;
import java.util.function.BiFunction;

public class FunctionSpecification<T> extends BasicSpecification<T> {

    private final BiFunction<T, BasicSpecification, Boolean> function;

    FunctionSpecification(String bean, String property, String message,
						  BiFunction<T, BasicSpecification, Boolean> function) {

        Objects.requireNonNull(function, "A função da especificação não pode ser nula");

        setBean(bean);
        setProperty(property);
        setMessage(message);
        this.function = function;
    }

    @Override
    public boolean isSatisfiedBy(T value) {
        return function.apply(value, this);
    }
}