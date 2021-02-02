package org.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.thinking.in.spring.ioc.domain.User;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT;
import static org.thinking.in.spring.lifecycle.LifecycleResourcesPath.USER_PROPERTIES;

/**
 * @author wcl
 * @date 4:35 下午 2020/10/13
 */
@Import(User.class)
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        configBeanDefinitionByProperties();
        configBeanDefinitionByXml();
        configBeanDefinitionByAPI();
        configBeanDefinitionByAnnotation();
    }

    /**
     * TODO 通过@Bean配置的BeanDefinition的class信息为null 为什么
     * TODO Bean注解，相同的BeanName只会去注册一次，为什么？
     *
     * 相同BeanName 注册BeanDefinition 后者会替换调前者
     * 如果BeanName 会执行 : this.beanDefinitionMap.put(beanName, beanDefinition);
     * @see DefaultListableBeanFactory#registerBeanDefinition(String, BeanDefinition)
     */
    @Bean
    public User hello() {
        return new User();
    }
//
//    @Bean("hello")
//    public User world() {
//        return new User();
//    }

    private static void configBeanDefinitionByAnnotation() {
        System.out.println("---------------通过Annotation配置BeanDefinition-------------------");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanMetadataConfigurationDemo.class);
//        context.scan("org.thinking.in.spring.ioc.domain");
        context.refresh();
        echoBeanDefinitionInfo(context);
        context.close();
    }

    private static void configBeanDefinitionByAPI() {
        System.out.println("---------------通过API配置BeanDefinition-------------------");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("age", 18)
                .addPropertyValue("name", "王岩")
                .addPropertyValue("scores", "11,22,-11")
                .getBeanDefinition();

        context.registerBeanDefinition("user", beanDefinition);
        context.registerBeanDefinition("user", beanDefinition);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, context);

        context.refresh();
        echoBeanDefinitionInfo(context);
        context.close();
    }

    private static void echoBeanDefinitionInfo(AnnotationConfigApplicationContext context) {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();


        Stream.of(beanDefinitionNames)
                .forEach(name -> System.out.println(name + " : " + context.getBeanDefinition(name)));
//                .map(context::getBeanDefinition)
//                .filter(bd -> User.class.getName().equals(bd.getBeanClassName()))
//                .forEach(System.out::println);
        // 依赖查找
        System.out.println(context.getBeansOfType(User.class).size());
    }

    private static void configBeanDefinitionByXml() {
        System.out.println("---------------通过XML配置BeanDefinition-------------------");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 基于 ClassPath 加载 XML 资源
        ClassPathResource classPathResource = new ClassPathResource(DEPENDENCY_LOOKUP_CONTEXT);
        // 指定字符编码 UTF-8
        EncodedResource resource = new EncodedResource(classPathResource, "UTF-8");
        int beanNumbers = reader.loadBeanDefinitions(resource);
        System.out.println("通过XML加载 BeanDefinition 的数量 : " + beanNumbers);
    }

    private static void configBeanDefinitionByProperties() {
        System.out.println("---------------通过properties配置BeanDefinition-------------------");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        // 解决中文乱码问题
        ClassPathResource classPathResource = new ClassPathResource(USER_PROPERTIES);
        EncodedResource resource = new EncodedResource(classPathResource, "UTF-8");
        System.out.println("通过properties加载 BeanDefinition 的数量 : " + reader.loadBeanDefinitions(resource));
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        Stream.of(beanDefinitionNames)
                .map(beanFactory::getBeanDefinition)
                .forEach(System.out::println);
        System.out.println(beanFactory.getBean("user"));
    }
}
