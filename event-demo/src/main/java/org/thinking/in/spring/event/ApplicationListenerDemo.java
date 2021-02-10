package org.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener}
 *
 * @author zms
 * @date 9:02 下午 2021/2/7
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);
        // 方法一：基于 spring 接口：向 spring 应用上下文注册事件
        // a、通过ConfigurableApplicationContext#addApplicationListener方法添加ApplicationListener
        context.addApplicationListener(event -> printf("ApplicationListener - 接收到 Spring 事件：" + event));
        // b、将ApplicationListener作为spring bean注册到容器中，例如：作为Configuration Class
        context.register(MyApplicationListener.class);
        // 方法二：基于 spring 注解 org.springframework.context.event.EventListener
        // 启动 spring 应用上下文
        context.refresh();
        // 启动 spring 上下文
        context.start();
        // 关闭 spring 上下文
        context.stop();
        // 关闭 spring 应用上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello world !") {
        });
        applicationEventPublisher.publishEvent("hello world");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            printf("MyApplicationListener - 接收到 Spring 事件：" + event);
        }
    }

    @EventListener
    @Async // 异步执行
    public void asyncOnApplicationEvent(ContextRefreshedEvent event) {
        printf("@EventListener - 接收到 ContextRefreshedEvent 事件 : " + event);
    }

    @EventListener
    @Order(2)
    @Async // 异步执行
    public void onApplicationEvent(ContextRefreshedEvent event) {
        printf("@EventListener 01- 接收到 ContextRefreshedEvent 事件 : " + event);
    }

    // TODO 如果异步执行没办法控制顺序？
    // 控制同一事件监听器的执行顺序
    @Order(-10)
    @EventListener
    @Async // 异步执行
    public void onApplicationEvent2(ContextRefreshedEvent event) {
        printf("@EventListener 02- 接收到 ContextRefreshedEvent 事件 : " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        printf("@EventListener - 接收到 ContextStartedEvent 事件 : " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event) {
        printf("@EventListener - 接收到 ContextStoppedEvent 事件 : " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        printf("@EventListener - 接收到 ContextClosedEvent 事件 : " + event);
    }


    private static void printf(Object printable) {
        System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
    }

}
