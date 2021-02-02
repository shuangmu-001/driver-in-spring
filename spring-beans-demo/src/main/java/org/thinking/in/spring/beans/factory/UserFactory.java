package org.thinking.in.spring.beans.factory;

import org.thinking.in.spring.ioc.domain.User;

/**
 * @author wcl
 * @date 10:38 上午 2020/6/11
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
