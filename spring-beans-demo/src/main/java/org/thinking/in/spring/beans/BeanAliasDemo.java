package org.thinking.in.spring.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.ioc.domain.User;

/**
 * 别名的意义在哪？ (业务区分)
 * @author wcl
 * @date 5:03 下午 2020/6/10
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        // 通过id user获取
        User user = beanFactory.getBean("user", User.class);
        // 通过别名 wy-user获取
        User bean = beanFactory.getBean("wy-user", User.class);
        System.out.println("wy-user 是否与 user 相同 : " + (user == bean));
    }

}
