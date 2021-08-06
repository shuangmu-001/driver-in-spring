package org.thinking.in.spring.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.beans.factory.UserFactoryBean;
import org.thinking.in.spring.ioc.domain.User;

/**
 * 实例化bean
 *
 * @author wcl
 * @date 10:28 上午 2020/6/11
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User bean = beanFactory.getBean("user-by-static-method", User.class);
        User userByFactoryMethod = beanFactory.getBean("user-by-factory-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        UserFactoryBean userFactoryBean = beanFactory.getBean("&user-by-factory-bean", UserFactoryBean.class);
        System.out.println("UserFactoryBean : " + userFactoryBean);
        System.out.println(bean);
        System.out.println(userByFactoryMethod);
        System.out.println(userByFactoryBean);
        System.out.println(userByFactoryMethod == bean);
        System.out.println(userByFactoryBean == bean);
    }

}
