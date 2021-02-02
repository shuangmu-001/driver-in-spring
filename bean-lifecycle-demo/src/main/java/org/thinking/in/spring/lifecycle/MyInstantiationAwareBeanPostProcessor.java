package org.thinking.in.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;
import org.thinking.in.spring.ioc.domain.SuperUser;
import org.thinking.in.spring.ioc.domain.User;
import org.thinking.in.spring.ioc.domain.UserHolder;

/**
 * TODO BeanPostProcessor什么时候被加载到容器里的
 *
 * @author zms
 * @date 3:58 下午 2021/1/25
 */
class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            return new SuperUser();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            User user = (User) bean;
            user.setAge(18);
            user.setName("wy");
            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            final MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }

            // 原始配置 <property name="description" value="The user holder" />
            if (propertyValues.contains("descriptor")) {
                propertyValues.removePropertyValue("descriptor");
            }
            // 等价于 <property name="number" value="1" />
            propertyValues.add("number", 18)
                    .add("descriptor", "修改PropertyValues的结果");

            return propertyValues;
        }
        return null;
    }

    // 返回什么对象都可以吗？ 是的，但是可能会造型错误 -> beanName对应的BeanDefinition中有className信息
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescriptor("执行初始化前的结果");
            return new SuperUser();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && SuperUser.class.equals(bean.getClass())) {
            return new User();
        }
        return bean;
    }
}
