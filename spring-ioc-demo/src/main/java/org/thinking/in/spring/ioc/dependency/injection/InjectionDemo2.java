package org.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.in.spring.ioc.dependency.injection.aware.AwareInjectionDemo;
import org.thinking.in.spring.ioc.dependency.injection.constructor.ConstructorInjectionDemo;
import org.thinking.in.spring.ioc.dependency.injection.constructor.F;
import org.thinking.in.spring.ioc.dependency.injection.field.FieldInjectionDemo;
import org.thinking.in.spring.ioc.dependency.injection.method.D;
import org.thinking.in.spring.ioc.dependency.injection.method.MethodInjectionDemo;
import org.thinking.in.spring.ioc.dependency.injection.setter.SetterInjectionDemo;

import javax.annotation.PostConstruct;

/**
 * @author wcl
 * @date 5:03 下午 2020/9/23
 */
public class InjectionDemo2 {

    @Autowired
    private AwareInjectionDemo awareInjectionDemo;
    @Autowired
    private ConstructorInjectionDemo constructorInjectionDemo;
    @Autowired
    private FieldInjectionDemo fieldInjectionDemo;
    @Autowired
    private MethodInjectionDemo methodInjectionDemo;
    @Autowired
    private SetterInjectionDemo setterInjectionDemo;

    @PostConstruct
    public void init() {
        System.out.println(awareInjectionDemo.getContext() == constructorInjectionDemo.getContext());
        System.out.println(awareInjectionDemo.getContext() == fieldInjectionDemo.getContext());
        System.out.println(awareInjectionDemo.getContext() == methodInjectionDemo.getContext());
        System.out.println(awareInjectionDemo.getContext() == setterInjectionDemo.getContext());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectionDemo2.class);

        context.scan("org.thinking.in.spring.ioc.dependency.injection");

        context.refresh();
        D d = context.getBean(D.class);
        System.out.println(d.getC());
        F f = context.getBean(F.class);
        f.getE();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName : " + beanDefinitionName + "; beanDefinition : " + context.getBeanDefinition(beanDefinitionName));
        }
        context.close();
    }

}
