package com.labtrackensino.javaweb.validation;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class ValidationMessage<T> {

    private String bean;
    private String property;
    private String message;
    private ConstraintViolation violation;
    private T source;

    public ValidationMessage(String message) {
        this(null, message);
    }

    public ValidationMessage(String message, ConstraintViolation violation) {
        this(null, message, violation);
    }

    public ValidationMessage(String bean, String message) {
        this(bean, null, message);
    }

    public ValidationMessage(String bean, String message, ConstraintViolation violation) {
        this(bean, null, message, violation);
    }

    public ValidationMessage(String bean, String message, T source) {
        this(bean, null, message, source);
    }

    public ValidationMessage(String bean, String property, String message) {
        this(bean, property, message, null, null);
    }

    public ValidationMessage(String bean, String property, String message,
							 ConstraintViolation violation) {
        this(bean, property, message, violation, null);
    }

    public ValidationMessage(String bean, String property, String message,
							 ConstraintViolation violation, T source) {
        this.bean = bean;
        this.property = property;
        this.message = message;
        this.violation = violation;
        this.source = source;
    }

    public ValidationMessage(String bean, String property, String message,
							 T source) {
        this(bean, property, message, null, source);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ValidationMessage)) {
            return false;
        }
        ValidationMessage other = (ValidationMessage) obj;
        if (bean == null) {
            if (other.bean != null) {
                return false;
            }
        } else if (!bean.equals(other.bean)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (property == null) {
            if (other.property != null) {
                return false;
            }
        } else if (!property.equals(other.property)) {
            return false;
        }
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (violation == null) {
            if (other.violation != null) {
                return false;
            }
        } else if (!violation.equals(other.violation)) {
            return false;
        }
        return true;
    }

    public String getBean() {
        return bean;
    }

    public String getMessage() {
        return message;
    }

    public String getProperty() {
        return property;
    }

    public T getSource() {
        return source;
    }

    public ConstraintViolation getViolation() {
        return violation;
    }

//    public String getErrorPath() {
//        String errorPath = "message";
//        if (StringUtils.isNotEmpty(getBean())) {
//            errorPath = getBean();
//
//            if (StringUtils.isNotEmpty(getProperty())) {
//                errorPath += "." + getProperty();
//            }
//        }
//
//        return errorPath;
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bean == null ? 0 : bean.hashCode());
        result = prime * result + (message == null ? 0 : message.hashCode());
        result = prime * result + (property == null ? 0 : property.hashCode());
        result = prime * result + (source == null ? 0 : source.hashCode());
        result = prime * result + (violation == null ? 0 : violation.hashCode());
        return result;
    }

    public static final class Builder<T> {

        private final Set<ValidationMessage> validationMessages = new LinkedHashSet<>();

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder add(final String message) {
            add(null, message);
            return this;
        }

        public Builder add(final String message, final ConstraintViolation violation) {
            add(null, null, message, violation, null);
            return this;
        }

        public Builder add(final String bean, final String message) {
            add(bean, null, message);
            return this;
        }

        public Builder add(final String bean, final String message,
						   final ConstraintViolation violation) {
            add(bean, null, message, violation, null);
            return this;
        }

        public Builder add(final String bean, final String message,
						   final T source) {
            add(bean, null, message, null, source);
            return this;
        }

        public Builder add(final String bean, final String property, final String message) {
            add(bean, property, message, null, null);
            return this;
        }

        public Builder add(final String bean, final String property, final String message,
						   ConstraintViolation violation) {
            add(bean, property, message, violation, null);
            return this;
        }

        public Builder add(final String bean, final String property, final String message,
						   final ConstraintViolation violation, final T source) {
            validationMessages.add(new ValidationMessage(bean, property, message, violation,
                    source));
            return this;
        }

        public Builder add(final String bean, final String property, final String message,
						   final T source) {
            add(bean, property, message, null, source);
            return this;
        }

        public Builder addAll(final Collection<ValidationMessage> messages) {
            validationMessages.addAll(messages);
            return this;
        }

        public ValidationResult build() {
            return ValidationResult.of(validationMessages);
        }

    }
}