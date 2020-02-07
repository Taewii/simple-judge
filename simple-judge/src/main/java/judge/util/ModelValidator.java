package judge.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public final class ModelValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ModelValidator() {
    }

    public static <M> boolean isValid(M model) {
        return validateModel(model) == null;
    }

    public static <M> String validateModel(M model) {
        Set<ConstraintViolation<M>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            return "Failed validation on:\r\n\t" +
                    violations.stream()
                            .map(cv -> cv.getPropertyPath().toString()
                                    + " (" + cv.getInvalidValue() + ") " + cv.getMessage())
                            .collect(Collectors.joining("\r\n\t"));
        }
        return null;
    }
}
