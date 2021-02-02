package org.thinking.in.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;
import org.thinking.in.spring.ioc.domain.User;

class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName)) {
            if (bean instanceof User) {
                User user = (User) bean;
                user.setName("zms");
            } else {
                System.out.println("销毁前userHolder的类名是:" + bean.getClass().getName());
                System.out.println("销毁前userHolder的信息:" + bean);
            }
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return true;
    }
}
