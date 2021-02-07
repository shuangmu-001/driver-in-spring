package org.thinking.in.spring.beans;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.thinking.in.spring.ioc.domain.User;

/**
 * {@link BeanDefinition} 构建示例
 * TODO 什么时候由谁创建的BeanDefinition，什么时候注册到BeanFactory里面？
 * @author wcl
 * @date 3:09 下午 2020/6/10
 *
 * @see AnnotationConfigUtils
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1、通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder
                .addPropertyValue("age", 18)
                .addPropertyValue("name", "王岩");
        System.out.println(builder.getBeanDefinition());

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        System.out.println(beanDefinition.getClass().getName());
        // 2、AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("age", 18)
                .add("name", "王岩");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }

}
