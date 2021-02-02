package org.thinking.in.spring.ioc.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Resolvable Dependency 作为依赖来源
 * @author wcl
 * @date 6:07 下午 2020/9/23
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ResolvableDependencySourceDemo.class);

        context.addBeanFactoryPostProcessor(beanFactory ->
            beanFactory.registerResolvableDependency(String.class, "Hello World")
        );

        context.refresh();


//        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
//
//        if(beanFactory instanceof ConfigurableListableBeanFactory) {
//            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
//
//            configurableListableBeanFactory.registerResolvableDependency(String.class, "HelloWorld");
//        }

        context.close();
    }

}
