package com.labtrackensino.javaweb.validation;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class ValidationException extends javax.validation.ValidationException {

    private final Set<ValidationMessage> validationMessages;

    protected ValidationException(Set<ValidationMessage> validationMessages) {
        super(validationMessages.stream().map(ValidationMessage::getMessage)
                .collect(Collectors.joining(", ")));
        this.validationMessages = validationMessages;
    }

    public Set<ValidationMessage> getValidationMessages() {
        return Collections.unmodifiableSet(validationMessages);
    }

}