package org.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.thinking.in.spring.ioc.domain.User;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT;

/**
 * @author wcl
 * @date 4:10 下午 2020/10/14
 * @see ConfigurableBeanFactory#getMergedBeanDefinition(String)
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 基于 ClassPath 加载 XML 资源
        ClassPathResource classPathResource = new ClassPathResource(DEPENDENCY_LOOKUP_CONTEXT);
        // 指定字符编码 UTF-8
        EncodedResource resource = new EncodedResource(classPathResource, "UTF-8");
        int beanNumbers = reader.loadBeanDefinitions(resource);
        System.out.println("已加载 BeanDefinition 数量 : " + beanNumbers);
        // 根据 Bean id和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
    }

}
