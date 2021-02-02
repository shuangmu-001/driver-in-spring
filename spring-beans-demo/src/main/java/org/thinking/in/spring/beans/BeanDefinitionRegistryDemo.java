package org.thinking.in.spring.beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.in.spring.ioc.domain.User;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

/**
 * 注册 {@link org.springframework.beans.factory.config.BeanDefinition}
 * 1、配置文件
 * 2、java api
 *      带名字注册，{@link BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)}
 *      不带名字注册 {@link BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition, BeanDefinitionRegistry)}
 * 3、java 注解
 *      {@link org.springframework.context.annotation.Bean}
 *      {@link org.springframework.context.annotation.Import}
 *      {@link org.springframework.stereotype.Component}
 *
 * 注册到哪去了? {@link DefaultListableBeanFactory#beanDefinitionMap}，
 * 在哪可以看到
 * 1、可以获取所有的beanDefinitionNames {@link DefaultListableBeanFactory#getBeanDefinitionNames()}
 * 2、在通过beanDefinitionNames获取beanDefinition {@link DefaultListableBeanFactory#getBeanDefinition(java.lang.String)}
 * @author wcl
 * @date 4:26 下午 2020/6/10
 */
public class BeanDefinitionRegistryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册bean
        registry(context, "user");
        registry(context, null);

        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
        Stream.of(beanDefinitionNames)
                .map(context::getBeanDefinition)
                .forEach(System.out::println);
        // 依赖查找
        System.out.println(context.getBeansOfType(User.class));

        context.close();
    }

    public static void registry(BeanDefinitionRegistry registry, String beanName) {

        AbstractBeanDefinition beanDefinition = getBeanDefinition();

        if(StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

    public static AbstractBeanDefinition getBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder
                .addPropertyValue("age", 18)
                .addPropertyValue("name", "王岩")
                .addPropertyValue("scores", "11,22,-11");
        return builder.getBeanDefinition();
    }


}
