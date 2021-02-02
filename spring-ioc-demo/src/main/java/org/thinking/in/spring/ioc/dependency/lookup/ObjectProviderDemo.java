package org.thinking.in.spring.ioc.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.ioc.domain.User;

import java.util.Map;

/**
 * 通过 ObjectProvider 查找
 * @author wcl
 * @date 11:10 上午 2020/6/17
 */
public class ObjectProviderDemo {

    @Autowired
    private ObjectProvider<Map<String, User>> objectProvider;

    @Value("${spring.application.name}")
    private String map;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ObjectProviderDemo.class);

        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        context.setParent(beanFactory);

        // 启动 spring 上下文
        context.refresh();

        // 延迟查找
        // TODO 存在的意义是什么？
//        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);

//        System.out.println("延迟查找 ObjectProvider : " + beanProvider);

//        System.out.println("延迟查找 user : " + beanProvider.getObject());

        ObjectProviderDemo bean = context.getBean(ObjectProviderDemo.class);

        System.out.println(bean.objectProvider.getObject());
        System.out.println(bean.map);

        // 关闭 spring 上下文
        context.close();
    }

    @Bean
    @Primary
    public User user() {
        return User.createUser();
    }

    @Bean
    public User user1() {
        return User.createUser();
    }

}
