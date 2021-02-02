package org.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT;
import static org.thinking.in.spring.lifecycle.BeanInstantiationLifecycleDemo.echoBeanInfo;
import static org.thinking.in.spring.lifecycle.LifecycleResourcesPath.CONSTRUCTOR_DEPENDENCY_INJECTION;


/**
 * @author zms
 * @date 8:10 下午 2021/1/23
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        System.out.println("----------------BeanFactory--------------------");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //添加 BeanPostProcessor 实现 MyDestructionAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //添加 BeanPostProcessor 实现 CommonAnnotationBeanPostProcessor 处理PostConstruct和PreDestroy
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        int beanNumbers = reader.loadBeanDefinitions(DEPENDENCY_LOOKUP_CONTEXT, CONSTRUCTOR_DEPENDENCY_INJECTION);
        System.out.println("已加载 BeanDefinition 数量 : " + beanNumbers);

        echoBeanInfo(beanFactory);
        Object userHolder = beanFactory.getBean("userHolder");
        beanFactory.destroySingleton("userHolder");
        System.out.println(userHolder);

    }

}
