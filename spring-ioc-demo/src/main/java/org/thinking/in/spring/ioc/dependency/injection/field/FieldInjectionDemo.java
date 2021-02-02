package org.thinking.in.spring.ioc.dependency.injection.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wcl
 * @date 4:56 下午 2020/9/23
 */
@Component
public class FieldInjectionDemo {

    @Autowired
    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }
}
