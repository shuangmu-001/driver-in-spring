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
     * @param message the object on which the event initially occurred or with
     *                which the event is associated (never {@code null})
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
