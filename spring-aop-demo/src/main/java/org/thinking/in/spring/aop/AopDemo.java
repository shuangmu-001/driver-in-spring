package org.thinking.in.spring.aop;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wcl
 * @date 5:16 下午 2021/5/10
 */
@EnableAspectJAutoProxy
public class AopDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/aop-context.xml");

        System.out.println(context.getBean(UserService.class));
    }
}
