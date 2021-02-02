package org.thinking.in.spring.beans.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wcl
 * @date 10:39 上午 2020/6/11
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : 初始化");
    }

    public void initMethod() {
        System.out.println("@Bean#initMethod() : 初始化");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean#afterPropertiesSet() : 初始化");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : 销毁...");
    }

    public void doDestroy() {
        System.out.println("@Bean#initMethod() : 销毁...");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean#destroy() : 销毁...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("BeanFactory 被回收");
    }
}
