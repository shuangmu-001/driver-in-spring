package org.thinking.in.spring.beans;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.in.spring.beans.factory.DefaultUserFactory;
import org.thinking.in.spring.beans.factory.UserFactory;

/**
 * @author wcl
 * @date 2:47 下午 2020/6/12
 */
public class SingletonBeanRegistryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 启动 spring 上下文
        context.refresh();

        UserFactory userFactory = new DefaultUserFactory();

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 注册外部singleton对象
        beanFactory.registerSingleton("userFactory", userFactory);

        // 依赖查找
        System.out.println(context.getBean("userFactory", UserFactory.class).createUser());

        // 关闭 spring 上下文
        context.close();
    }

}
