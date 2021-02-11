package org.thinking.in.spring.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zms
 * @date 8:59 下午 2021/2/10
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 1、添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringListener());
        // 2、启动 Spring 应用上下文
        context.refresh();
        // 3、发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello world !"));
        // 4、关闭 Spring 应用上下文
        context.close();
    }

}
