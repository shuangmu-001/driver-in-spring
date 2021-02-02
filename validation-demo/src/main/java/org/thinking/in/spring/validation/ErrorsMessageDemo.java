package org.thinking.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.thinking.in.spring.ioc.domain.User;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author zms
 * @date 6:19 下午 2021/1/29
 */
public class ErrorsMessageDemo {

    public static void main(String[] args) {
        // 创建User对象
        User user = new User();
        user.setName("wy");
        // 创建Errors关联User
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 调用reject或rejectValue方法
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");
        // 获取Errors对象中ObjectError或FieldError
        List<ObjectError> allErrors = errors.getAllErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        // 将ObjectError或FieldError中的code和args，关联MessageSource实现（如：`ResourceBundleMessageSource`）
        MessageSource messageSource = createMessageSource();
        allErrors.forEach(error -> System.out.println(messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault())));
    }

    public static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of user must not be null");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of user must not be null");
        return messageSource;
    }

}
