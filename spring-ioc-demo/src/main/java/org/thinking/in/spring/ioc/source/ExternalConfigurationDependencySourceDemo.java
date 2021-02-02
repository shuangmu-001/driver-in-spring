package org.thinking.in.spring.ioc.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author wcl
 * @date 6:21 下午 2020/9/23
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath:/default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ExternalConfigurationDependencySourceDemo.class);

        context.refresh();

        ExternalConfigurationDependencySourceDemo bean = context.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("user.id : " + bean.id);
        System.out.println("user.name : " + bean.name);
        System.out.println("user.resource : " + bean.resource);

        context.close();
    }
}
