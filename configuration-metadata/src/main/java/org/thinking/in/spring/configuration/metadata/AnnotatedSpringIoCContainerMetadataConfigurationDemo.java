package org.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.thinking.in.spring.ioc.domain.User;

import java.util.HashMap;
import java.util.Map;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT;

/**
 * 基于 java 注解 Spring IoC 容器元信息配置
 *
 * @author zms
 * @date 2:09 下午 2021/1/27
 */
@Import(User.class)
@ImportResource(DEPENDENCY_LOOKUP_CONTEXT)
//@PropertySource("classpath:/META-INF/default.properties")
//@PropertySource("classpath:/META-INF/default.properties")
@PropertySources(@PropertySource("classpath:/META-INF/default.properties"))
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();

        Map<String, Object> map = new HashMap<>();

        map.put("user.name", "wy");
        org.springframework.core.env.PropertySource mapPropertySource = new MapPropertySource("first-property", map);

        environment.getPropertySources().addFirst(mapPropertySource);
        context.refresh();

        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        beansOfType.forEach((key, value) -> System.out.println(key + " : " + value));

        // 资源管理
        System.out.println(environment.getPropertySources());
        context.close();
    }

}
