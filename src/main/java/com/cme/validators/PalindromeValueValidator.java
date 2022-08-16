package com.cme.validators;

import org.apache.commons.lang3.StringUtils;
import utils.AppUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PalindromeValueValidator implements ConstraintValidator<ValidatePalindrome, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(getConditionalMessage(value))
                .addConstraintViolation();

        return StringUtils.isNotBlank(value) && isWord(value) && !isNumeric(value) && AppUtils.isPalindrome(value);
    }

    private String getConditionalMessage(String value) {
        String message = "";
        if (StringUtils.isBlank(value)) {
            message = "palindrome should NOT be blank/null";
        } else if (isNumeric(value)) {
            message = "palindrome should NOT be a number";
        } else if (!isWord(value)) {
            message = "palindrome should NOT have leading/trailing/in-between spaces. Should be a single word";
        } else if (!AppUtils.isPalindrome(value)) {
            message = "palindrome should be same as reverse of the word";
            System.out.println(message);
        }
        return message;
    }

    private boolean isWord(String value) {        
        return Pattern.matches("(\\w+)|(\\s)", value);
    }

    private boolean isNumeric(String value) {
        return Pattern.matches("-?\\d+(\\.\\d+)?", value);
    }

}