package org.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * TODO FactoryBean 和 BeanFactory 的关系
 * @author zms
 * @date 3:41 下午 2021/1/27
 */
public class XMLBaseYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int count = beanDefinitionReader.loadBeanDefinitions("META-INF/yaml-property-source.xml");
        System.out.println("加载BeanDefinition数量 : " + count);
        Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(yamlMap);
    }

}
