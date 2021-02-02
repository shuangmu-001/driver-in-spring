package org.thinking.in.spring.ioc.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 * 依赖注入 比 依赖查找 多了一个 非spring容器的bean 可以通过调api注入
 * {@link org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}
 * {@link ConfigurableListableBeanFactory#registerResolvableDependency(java.lang.Class, java.lang.Object)}
 * @author wcl
 * @date 5:17 下午 2020/9/22
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DependencySourceDemo.class);
        context.refresh();
        getBean(context, BeanFactory.class);
        getBean(context, ApplicationContext.class);
        getBean(context, ResourceLoader.class);
        getBean(context, ApplicationEventPublisher.class);
        context.close();
    }

    @PostConstruct
    public void initByInjection() {
        System.out.println("applicationContext == beanFactory : " + (applicationContext == beanFactory));
        System.out.println("applicationContext.getBeanFactory() == beanFactory : " + (applicationContext.getAutowireCapableBeanFactory() == beanFactory));
        System.out.println("applicationContext == resourceLoader : " + (resourceLoader == applicationContext));
        System.out.println("applicationContext == applicationEventPublisher : " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    public <T> T getBean(Class<T> beanType) {
        try {
            return applicationContext.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在 BeanFactory 中查找！");
        }
        return null;
    }

    public static <T> T getBean(BeanFactory beanFactory, Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在 BeanFactory 中查找！");
        }
        return null;
    }
}
