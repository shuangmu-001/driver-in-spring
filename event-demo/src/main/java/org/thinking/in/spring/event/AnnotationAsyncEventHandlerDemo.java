package org.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * 注解驱动异步事件处理器示例
 * {@link EnableAsync} 激活 Spring 异步特性
 *
 * @author zms
 * @date 6:05 下午 2021/2/23
 */
@EnableAsync
public class AnnotationAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 1、注册当前类作为 Configuration Class
        context.register(AnnotationAsyncEventHandlerDemo.class);
        // 2、启动 Spring 应用上下文
        context.refresh();
        // 3、发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello world !"));
        // 4、关闭 Spring 应用上下文
        context.close();
    }

    /**
     * {@link Async} 同步切换成异步
     */
    @Async
    @EventListener
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程：%s] : 监听到事件 %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public ExecutorService taskExecutor() {
        return newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
    }
}
