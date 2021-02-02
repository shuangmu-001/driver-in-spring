package org.thinking.in.spring.ioc.dependency.injection.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wcl
 * @date 5:00 下午 2020/9/23
 */
@Component
public class SetterInjectionDemo {

    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
