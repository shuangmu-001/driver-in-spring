package org.thinking.in.spring.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.thinking.in.spring.beans.factory.DefaultUserFactory;
import org.thinking.in.spring.beans.factory.UserFactory;

/**
 * @author wcl
 * @date 3:13 下午 2020/6/12
 */
// @Configuration 不是必须的
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(BeanGarbageCollectionDemo.class);

        System.out.println("spring 上下文准备启动");
        // 启动 spring 上下文
        context.refresh();

        System.out.println("spring 上下文启动");
        // 依赖查找
        System.out.println(context.getBean("userFactory", UserFactory.class).createUser());

        System.out.println("spring 上下文准备关闭");

        // 关闭 spring 上下文
        context.close();

        System.out.println("spring 上下文关闭");

        System.gc();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }


}
