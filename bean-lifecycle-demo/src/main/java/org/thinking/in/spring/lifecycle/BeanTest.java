package org.thinking.in.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.thinking.in.spring.ioc.domain.User;

/**
 * @author zms
 * @date 5:20 下午 2021/2/5
 */
public class BeanTest {
    @Bean("hello")
    public static User world() {
        User user = new User();
        user.setId(100L);
        user.setName("wy");
        return user;
    }

}
