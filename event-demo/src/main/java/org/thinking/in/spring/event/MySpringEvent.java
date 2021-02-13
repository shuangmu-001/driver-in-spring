package org.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zms
 * @date 9:17 下午 2021/2/10
 */
public class MySpringEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message 事件信息
     */
    public MySpringEvent(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return super.getSource().toString();
    }

    public String getMessage() {
        return getSource();
    }
}
