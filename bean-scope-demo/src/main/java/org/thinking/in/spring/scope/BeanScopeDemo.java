package org.thinking.in.spring.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.thinking.in.spring.ioc.domain.User;

import java.util.Map;

/**
 * @author wcl
 * @date 4:09 下午 2020/9/25
 * - 结论一
 *   - Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
 *   - Prototype Bean 无论依赖查找还是依赖注入，均为新生成的对象
 * - 结论二
 *   - 如果依赖注入集合对象，Singleton Bean 和 Prototype Bean 均会存在一个
 * - 结论三
 *   - Singleton Bean 和 Prototype Bean 都会执行初始化操作，但是Prototype Bean不会执行销毁操作
 */
public class BeanScopeDemo implements DisposableBean {

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s bean 名称 %s 在初始化后回调.... \n", bean.getClass(), beanName);
                    return null;
                }
            });
        });

        applicationContext.refresh();
        scopeBeanByLookup(applicationContext);
        scopeBeanByInjection(applicationContext);
        applicationContext.close();
    }

    public static void scopeBeanByLookup(ApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            System.out.println(context.getBean("singletonUser"));
            System.out.println(context.getBean("prototypeUser"));
        }
    }

    public static void scopeBeanByInjection(ApplicationContext context) {
        BeanScopeDemo beanScopeDemo = context.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser : " + beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser1 : " + beanScopeDemo.singletonUser1);

        System.out.println("beanScopeDemo.prototypeUser : " + beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1 : " + beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2 : " + beanScopeDemo.prototypeUser2);

        System.out.println("beanScopeDemo.users : " + beanScopeDemo.users);
    }

    @Bean
    public User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo Bean在销毁中");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        users.forEach((key, value) -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(key);
            if(beanDefinition.isPrototype()) {
                value.destroy();
            }
        });
        System.out.println("当前BeanScopeDemo Bean销毁完成");
    }
}
