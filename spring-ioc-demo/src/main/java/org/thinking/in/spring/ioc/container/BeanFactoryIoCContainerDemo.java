package org.thinking.in.spring.ioc.container;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.thinking.in.spring.ioc.domain.User;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_INJECTION_CONTEXT;

/**
 * @author wcl
 * @date 10:19 上午 2020/6/10
 */
public class BeanFactoryIoCContainerDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // the number of bean definitions found
        System.out.println(reader.loadBeanDefinitions(DEPENDENCY_INJECTION_CONTEXT));
        System.out.println(beanFactory.getBeansOfType(User.class));
    }

}
