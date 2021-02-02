package org.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.thinking.in.spring.ioc.domain.City;
import org.thinking.in.spring.ioc.domain.User;

/**
 * @author zms
 * @date 4:03 下午 2021/1/27
 */
@PropertySource(name = "yaml", value = "classpath:/META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name,
                     @Value("${user.city}") City[] cities
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCities(cities);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedYamlPropertySourceDemo.class);

        context.refresh();

        context.getEnvironment()
                .getPropertySources()
                .stream()
                .map(org.springframework.core.env.PropertySource::getSource)
                .forEach(System.out::println);


        System.out.println();

        User user = context.getBean("user", User.class);
        System.out.println(user);

        context.close();
    }


}
