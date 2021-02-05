package org.thinking.in.spring.conversion;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.thinking.in.spring.ioc.domain.User;

/**
 * @author zms
 * @date 5:58 下午 2021/2/3
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/conversion-context.xml");
        System.out.println(context.getBean("user", User.class));

        System.out.println(context.getBean(ConversionService.class).getClass());
        context.close();
    }

}
