package org.thinking.in.spring.ioc.container;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.thinking.in.spring.ioc.domain.User;

import java.util.stream.Stream;

/**
 * @author wcl
 * @date 10:24 上午 2020/6/10
 */
//@Import(ApplicationContextIoCContainerDemo.Config.class)
public class ApplicationContextIoCContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config.class);

//        context.scan("org.thinking.ioc.container");

        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
        Stream.of(beanDefinitionNames).map(context::getBeanDefinition).forEach(System.out::println);

        System.out.println(context.getBeansOfType(Config.class).size());

        System.out.println(context.getBeansOfType(User.class).size());

        context.close();
    }

    @Component
    public static class Config {

        @Bean
        @Primary
        public User user() {
            User user = new User();
            user.setAge(18);
            user.setName("王岩");
            return user;
        }

        @Bean(name = "hello")
        public User user1() {
            User user = new User();
            user.setAge(18);
            user.setName("王岩2");
            return user;
        }


        @Bean(name = "hello3")
        public User user3() {
            User user = new User();
            user.setAge(18);
            user.setName("王岩3");
            return user;
        }

        @Bean("world")
        public User user2(@Qualifier("hello") User user, @Qualifier("hello3") User user1) {
            System.out.println(user);
            System.out.println(user1);
            return user;
        }

        @Bean
        public Config config() {
            return new Config();
        }
    }

}
