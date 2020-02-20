package com.labtrackensino.javaweb.validation;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public final class SpecificationValidator<T> {

    private final List specifications = new ArrayList<>();

    private final ApplicationContext applicationContext;

    private SpecificationValidator(ApplicationContext applicationContext) {
        Objects.requireNonNull(applicationContext);
        this.applicationContext = applicationContext;
    }

    public static <T> SpecificationValidator<T> create(ApplicationContext applicationContext) {
        return new SpecificationValidator<>(applicationContext);
    }

    public SpecificationValidator<T> addSpecification(ISpecification specification) {
        if (nonNull(specification)) {
            this.specifications.add(specification);
        }
        return this;
    }

    public SpecificationValidator<T> addSpecifications(List<ISpecification> specifications) {
        if (nonNull(specifications)) {
            this.specifications.addAll(specifications);
        }
        return this;
    }

    public SpecificationValidator<T> addSpecificationClass(Class<? extends ISpecification> specification) {
        if (nonNull(specification)) {
            this.specifications.add(specification);
        }
        return this;
    }

    public SpecificationValidator<T> addSpecificationClasses(List<Class<? extends ISpecification>> specifications) {
        if (nonNull(specifications)) {
            this.specifications.addAll(specifications);
        }
        return this;
    }

    public SpecificationValidator<T> clear() {
        this.specifications.clear();
        return this;
    }

    public SpecificationValidator<T> remove(ISpecification s) {
        specifications.remove(s);
        return this;
    }

    public SpecificationValidator<T> remove(Class<? extends ISpecification> s) {
        specifications.remove(s);
        return this;
    }

    public int count() {
        return specifications.size();
    }

    public void validateWithException(final T candidate) {
        validateWithException(candidate, false);
    }

    public void validateWithException(final T candidate, boolean stopOnError) {
        this.validate(candidate, stopOnError).throwMessages();
    }

    /**
     * Valida todas as specifications, sem interrupção em caso de erro.
     *
     * @param candidate
     * @return
     */
    public ValidationResult validate(final T candidate) {
        return validate(candidate, false);
    }

    /**
     * Valida todas as specifications, respeitando o parâmetro {@code stopOnError}, pararando a
     * validação caso encontre algum erro.
     *
     * @param candidate
     * @param stopOnError
     * @return
     */
    @SuppressWarnings("unchecked")
    public ValidationResult validate(final T candidate, boolean stopOnError) {
        final ValidationMessage.Builder<ISpecification> builder = ValidationMessage.Builder.create();
        for (Object obj : this.specifications) {
            ISpecification specification = getSpecification(obj);
            if (!specification.isSatisfiedBy(candidate)) {

                String beanStr = specification.getBean();
                String messageStr = specification.getMessage();
                if (beanStr == null) {
                    beanStr = candidate.getClass().getSimpleName();
                }
                if (messageStr == null) {
                    messageStr = specification.toString();
                }
                builder.add(beanStr, specification.getProperty(), messageStr, specification);

                if (stopOnError) {
                    break;
                }
            }
        }
        return builder.build();
    }

    private ISpecification getSpecification(Object obj) {
        if (obj instanceof ISpecification) {
            return (ISpecification) obj;
        } else if (obj instanceof Class) {
            return applicationContext.getBean((Class<ISpecification>) obj);
        }

        throw new IllegalArgumentException("Não foi possível inicializar a especificação de validação " + obj);
    }
}