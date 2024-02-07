package com.image.resizer.validator;

import com.image.resizer.constraint.ValidImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    @Override
    public void initialize(ValidImage constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String ct = file.getContentType();

        if (ct != null && ct.startsWith("image/")) {
            return true;
        }

        context.buildConstraintViolationWithTemplate("Invalid image file")
            .addPropertyNode(file.getName())
            .addConstraintViolation();

        return false;
    }
}
