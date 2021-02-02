package org.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.ioc.repository.UserRepository;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_INJECTION_CONTEXT;

/**
 * @author wcl
 * @date 11:54 上午 2020/6/9
 * ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
 * @see ConfigurableApplicationContext#getBeanFactory()
 */
public class InjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(DEPENDENCY_INJECTION_CONTEXT);

        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");

        System.out.println(userRepository);

        System.out.println(userRepository.getBeanFactory());

        System.out.println(userRepository.getBeanFactory() == beanFactory);

        // 依赖查找(错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject() == beanFactory);

        System.out.println(objectFactory.getObject());
    }

}
