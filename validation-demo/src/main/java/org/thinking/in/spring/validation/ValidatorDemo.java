package org.thinking.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thinking.in.spring.ioc.domain.User;

import javax.activation.UnsupportedDataTypeException;
import java.util.Locale;
import java.util.Objects;

import static org.thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * @author zms
 * @date 3:49 下午 2021/2/1
 */
public class ValidatorDemo {

    public static void main(String[] args) throws UnsupportedDataTypeException {
        // 1、创建 Validator
        UserValidator userValidator = new UserValidator();
        // 2、判断是否支持目标对象的类型
        if (!userValidator.supports(User.class)) {
            throw new UnsupportedDataTypeException();
        }
        User user = new User();
        // 3、创建Errors
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);
        // 4、获取MessageSource对象
        MessageSource messageSource = createMessageSource();
        // 5、输出错误文案
        errors.getAllErrors().forEach(error -> System.out.println(messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault())));

    }

}

class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
    }
}
