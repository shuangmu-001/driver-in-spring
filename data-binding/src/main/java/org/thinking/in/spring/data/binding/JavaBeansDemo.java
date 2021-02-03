package org.thinking.in.spring.data.binding;

import java.beans.*;
import java.util.EventListener;
import java.util.stream.Stream;

/**
 * @author zms
 * @date 3:03 下午 2021/2/3
 */
public class JavaBeansDemo {

    private String username;

    public static void main(String[] args) throws IntrospectionException {
        // stopClass 及以上的方法获取不到
        BeanInfo beanInfo = Introspector.getBeanInfo(JavaBeansDemo.class);
        // PropertyDescriptor只针对方法 get、is(读) 或set(写)，可以不成对出现，即至少又一个读或写
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        System.out.println("-----------property------------");
        Stream.of(propertyDescriptors)
                .forEach(System.out::println);
        // 1、add和remove方法后缀与参数的全类名后缀相同, 同时名字必须以Listener结尾
        // public void add<Event>Listener(<Event>Listener a)
        // public void remove<Event>Listener(<Event>Listener a)
        // 2、add和remove必须同时存在
        EventSetDescriptor[] eventSetDescriptors = beanInfo.getEventSetDescriptors();
        System.out.println("-----------eventSetDescriptors------------");
        Stream.of(eventSetDescriptors)
                .forEach(System.out::println);

        System.out.println("-----------listener------------");
        JavaBeansDemo javaBeansDemo = new JavaBeansDemo();
        PropertyChangeListener ecl = System.out::println;
        javaBeansDemo.addPropertyChangeListener(ecl);
        javaBeansDemo.addPropertyChangeListener(e -> System.out.println(e.getNewValue()));
        javaBeansDemo.setUsername("hello");
        System.out.println("----------all listener---------------");
        PropertyChangeListener[] propertyChangeListeners = javaBeansDemo.getPropertyChangeListeners();
        Stream.of(propertyChangeListeners)
                .forEach(System.out::println);

        javaBeansDemo.removePropertyChangeListener(ecl);
        System.out.println("-----------remove listener------------");
        javaBeansDemo.setUsername("world");
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * 启动属性（生效）变化
     */
    private void firePropertyChange(String oldValue, String newValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "username", oldValue, newValue);
        propertyChangeSupport.firePropertyChange(event);
    }

    /**
     * 添加属性（生效）变化监听器
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * 删除属性（生效）变化监听器
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * 获取属性（生效）变化监听器
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    public void setUsername(String username) {
        String oldValue = this.username;
        this.username = username;
        firePropertyChange(oldValue, username);
    }

}

class HelloListener implements EventListener {

}
