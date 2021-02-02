package org.thinking.in.spring.beans.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.thinking.in.spring.ioc.domain.User;


/**
 * @author wcl
 * @date 10:44 上午 2020/6/11
 */
public class UserFactoryBean extends AbstractFactoryBean<User> {

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    protected User createInstance() {
        return User.createUser();
    }

}
