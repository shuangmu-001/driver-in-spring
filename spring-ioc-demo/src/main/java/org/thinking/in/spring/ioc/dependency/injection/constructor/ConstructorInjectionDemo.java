package org.thinking.in.spring.ioc.dependency.injection.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wcl
 * @date 4:56 下午 2020/9/23
 */
@Component
public class ConstructorInjectionDemo {

    private final ApplicationContext context;

    public ConstructorInjectionDemo(ApplicationContext context) {
        this.context = context;
    }

    public ApplicationContext getContext() {
        return context;
    }
}
