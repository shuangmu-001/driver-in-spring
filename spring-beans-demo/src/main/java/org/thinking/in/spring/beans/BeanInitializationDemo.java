package org.thinking.in.spring.beans;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.thinking.in.spring.beans.factory.DefaultUserFactory;
import org.thinking.in.spring.beans.factory.UserFactory;

/**
 * Bean 初始化
 *      正常 (spring 上下文启动的时候初始化)
 *          1、{@link javax.annotation.PostConstruct}
 *              通过 {@link BeanPostProcessor#postProcessBeforeInitialization(Object, String)}实现的
 *              {@link org.springframework.context.annotation.CommonAnnotationBeanPostProcessor}
 *              {@link org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor#postProcessBeforeInitialization(Object, String)}
 *          2、{@link org.springframework.beans.factory.InitializingBean#afterPropertiesSet()}
 *          3、{@link org.springframework.context.annotation.Bean#initMethod()}
 *      延迟 (调用的时候初始化)
 *          1、{@link org.springframework.context.annotation.Lazy}
 * Bean 销毁 (spring 上下文关闭的时候触发销毁)
 * 1、{@link javax.annotation.PreDestroy}
 * 2、{@link org.springframework.beans.factory.DisposableBean#destroy()}
 * 3、{@link org.springframework.context.annotation.Bean#destroyMethod()}
 * @author wcl
 * @date 11:36 上午 2020/6/11
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(BeanInitializationDemo.class);

        System.out.println("spring 上下文准备启动");
        // 启动 spring 上下文
        context.refresh();

        System.out.println("spring 上下文启动");
        // 依赖查找
        UserFactory userFactory = context.getBean("userFactory", UserFactory.class);

        System.out.println("spring 上下文准备关闭");

        // 关闭 spring 上下文
        context.close();

        System.out.println("spring 上下文关闭");
    }

    @Bean(initMethod = "initMethod", destroyMethod = "doDestroy")
    @Lazy(false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
