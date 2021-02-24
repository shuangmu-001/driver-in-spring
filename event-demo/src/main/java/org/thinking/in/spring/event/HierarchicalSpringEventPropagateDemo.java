package org.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Spring 层次性上下文事件传播
 *
 * @author zms
 * @date 3:07 下午 2021/2/8
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 创建 parent spring 应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        // 注册 MyListener 到 parent spring 应用上下文
        parentContext.register(MyListener.class);
        // 创建 current spring 应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        // current -> parent
        currentContext.setParent(parentContext);
        // 注册 MyListener 到 current spring 应用上下文
        currentContext.register(MyListener.class);
        // 启动 parent spring 应用上下文
        parentContext.refresh();
        // 启动 current spring 应用上下文
        currentContext.refresh();
        // 关闭 current spring 应用上下文
        currentContext.close();
        // 关闭 parent spring 应用上下文
        parentContext.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {
        // 是否被处理过
        private boolean processed;

        private static final Set<ApplicationContextEvent> events = new HashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (events.add(event)) {
                System.out.printf("监听到 spring 应用上下文[ID : %s] 的事件 : %s \n",
                        event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }

}
