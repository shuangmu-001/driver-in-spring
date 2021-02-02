package org.thinking.in.spring.beans;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.beans.factory.DefaultUserFactory;
import org.thinking.in.spring.beans.factory.UserFactory;

import java.util.ServiceLoader;

/**
 * @author wcl
 * @date 10:29 上午 2020/6/11
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        // 通过 AutowireCapableBeanFactory 实例化
        AutowireCapableBeanFactory autowireCapableBeanFactory = beanFactory.getAutowireCapableBeanFactory();
        // 不能是抽象接口
        UserFactory bean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

        // 通过ServiceLoaderFactoryBean
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("serviceLoader", ServiceLoader.class);
        demoServiceLoader(serviceLoader);
        demoServiceLoader();
    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class);
        demoServiceLoader(load);
    }

    private static void demoServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        for (UserFactory userFactory : serviceLoader) {
            System.out.println(userFactory.createUser());
        }
    }

}
