package org.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @author zms
 * @date 6:07 下午 2021/2/7
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Observable observable = new EventObservable();
        // 添加观察者（监听者）
        observable.addObserver(new EventObserver());
        // 发布消息（事件）
        observable.notifyObservers("hello world");
    }

    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }

        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            EventObject event = (EventObject) arg;
            System.out.println("收到事件 : " + event.getSource());
        }
    }

}
