package org.thinking.in.spring.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * @author zms
 * @date 4:18 下午 2021/2/1
 * @see Validator
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        // 启动spring应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");
        Validator validator = context.getBean(Validator.class);
        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = context.getBean(UserProcessor.class);
        userProcessor.processor(new User());
        // 关闭spring 上下文
        context.close();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void processor(@Valid User user) {
            System.out.println(user);
        }
    }

}

class User {
    @NotNull
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
