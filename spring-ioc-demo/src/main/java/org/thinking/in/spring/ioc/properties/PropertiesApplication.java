package org.thinking.in.spring.ioc.properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.thinking.in.spring.ioc.domain.User;

import java.util.Arrays;

import static org.thinking.in.spring.ioc.utils.ResourcesPath.PROPERTIES_CONTEXT;

/**
 * @author wcl
 * @date 8:48 下午 2020/7/4
 */
public class PropertiesApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(PROPERTIES_CONTEXT);
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> source : propertySources) {
            System.out.println(source);
        }
        User user = (User) context.getBean("user");
        System.out.println(Arrays.toString(user.getScores()));
    }

}
