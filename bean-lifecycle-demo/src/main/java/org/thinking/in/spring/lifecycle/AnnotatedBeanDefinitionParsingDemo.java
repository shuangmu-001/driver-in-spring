package org.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.stereotype.Component;

//import javax.annotation.ManagedBean;
//import javax.inject.Named;

/**
 * @author wcl
 * @date 6:21 下午 2020/10/13
 */
//@Service("annotationBeanDefinitionParsingDemo")
//@Controller("annotationBeanDefinitionParsingDemo")
//@Repository("annotationBeanDefinitionParsingDemo")
@Component("annotationBeanDefinitionParsingDemo")
//@ManagedBean("annotationBeanDefinitionParsingDemo")
//@Named("annotationBeanDefinitionParsingDemo")
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 java 注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        // 注册的个数
        System.out.println("已加载 BeanDefinition 的数量 : " + (beanDefinitionCountAfter - beanDefinitionCountBefore));
        // 普通class 作为Component注册到spring ioc 容器 bean的名称为 className首字母小写
        // Bean 名称生成来源于 BeanNameGenerator 注解实现 AnnotationBeanNameGenerator
        System.out.println(beanFactory.getBean("annotationBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class));
    }
}
