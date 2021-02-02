package org.thinking.in.spring.lifecycle;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.ioc.domain.User;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT;
import static org.thinking.in.spring.lifecycle.LifecycleResourcesPath.CONSTRUCTOR_DEPENDENCY_INJECTION;

/**
 * @author wcl
 * @date 5:11 下午 2020/10/14
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        System.out.println("----------------BeanFactory--------------------");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanNumbers = reader.loadBeanDefinitions(DEPENDENCY_LOOKUP_CONTEXT, CONSTRUCTOR_DEPENDENCY_INJECTION);

        System.out.println("已加载 BeanDefinition 数量 : " + beanNumbers);

        echoBeanInfo(beanFactory);
    }

    /**
     * 1、在获取
     * @see AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)
     */
    private static void executeApplicationContext() {
        System.out.println("----------------ApplicationContext--------------------");
        String[] location = {DEPENDENCY_LOOKUP_CONTEXT, CONSTRUCTOR_DEPENDENCY_INJECTION};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);

        context.refresh();
        System.out.println("通过依赖查找对象");
        echoBeanInfo(context);

        context.close();
    }

    public static void echoBeanInfo(BeanFactory beanFactory) {
        // TODO 依赖查找的时候才创建bean对象吗？
        // BeanFactory是在依赖查找的时候才会创建bean对象
        // TODO ApplicationContext掉用refresh时会创建bean对象
        // 根据 Bean id和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        System.out.println("--------------------------------");
        System.out.println("准备获取userHolder");
//        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        Object userHolder = beanFactory.getBean("userHolder");
        System.out.println(userHolder);
    }

}


