package com.labtrackensino.javaweb.validation;

public abstract class BasicSpecification<T> implements ISpecification<T, BasicSpecification<T>> {

    private String bean;
    private String property;
    private String message;

    @Override
    public String getBean() {
        return bean;
    }

    @Override
    public String getProperty() {
        return property;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public BasicSpecification<T> setBean(String bean) {
        this.bean = bean;
        return this;
    }

    @Override
    public BasicSpecification<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public BasicSpecification<T> setProperty(String property) {
        this.property = property;
        return this;
    }

}
