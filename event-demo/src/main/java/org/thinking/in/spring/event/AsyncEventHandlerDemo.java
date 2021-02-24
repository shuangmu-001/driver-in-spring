package org.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * 异步事件处理器示例
 *
 * @author zms
 * @date 6:05 下午 2021/2/23
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 1、添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringListener());
        // 2、启动 Spring 应用上下文
        // 初始化 ApplicationEventMulticaster
        context.refresh();
        // 依赖查找 ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        // 判断当前 ApplicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            // 切换 TaskExecutor
            ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
            // 同步 -> 异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
            // 添加 ContextClosedEvent
            context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if (!taskExecutor.isShutdown()) {
                    taskExecutor.shutdown();
                }
            });
        }
        // 3、发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello world !"));
        // 4、关闭 Spring 应用上下文
        context.close();
    }
}
