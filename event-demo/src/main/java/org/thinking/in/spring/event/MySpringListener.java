package org.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @author zms
 * @date 9:16 下午 2021/2/10
 */
public class MySpringListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程：%s] : 监听到事件 %s\n", Thread.currentThread().getName(), event);
    }
}
