package org.thinking.in.spring.ioc.dependency.injection.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wcl
 * @date 4:58 下午 2020/9/23
 */
@Component
public class MethodInjectionDemo {

    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    @Autowired
    public void injectContext(ApplicationContext context) {
        this.context = context;
    }
}
