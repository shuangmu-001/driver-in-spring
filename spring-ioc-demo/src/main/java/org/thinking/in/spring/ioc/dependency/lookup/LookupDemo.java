package org.thinking.in.spring.ioc.dependency.lookup;

import org.springframework.beans.factory.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thinking.in.spring.ioc.domain.User;
import org.thinking.in.spring.ioc.annotation.Super;
import org.thinking.in.spring.ioc.utils.ResourcesPath;

import java.util.Map;

/**
 * @author wcl
 * @date 11:04 上午 2020/6/9
 *
 * {@link BeanFactory} 单一查找的核心
 * 单一查找
 *      根据名称，类型，名称和类型，延迟查找(5.1)
 *
 * {@link ListableBeanFactory} 集合查找的核心
 * 集合查找
 *      根据bean类型查找
 *          获取同类型bean名称列表
 *          获取同类型bean实例列表
 *      根据注解类型查找(3.0) 注解和名称两重判断
 *          获取标注类型bean名称列表
 *          获取标注类型bean实例列表
 *          获取指定类型+名称Bean实例
 * bean的生命周期很重要 beanDefinition
 * TODO ResolvableType
 * TODO bean 定义默认可以被覆盖 (BeanDefinition 被覆盖？)
 * TODO bean 名称和实例那个重要
 * TODO {@link ListableBeanFactory#getBeansOfType(Class)} 造成bean提前初始化
 *
 * {@link HierarchicalBeanFactory}
 * 层次性查找
 *      根据bean名称查找
 *          基于containsLocalBean方法实现
 *      根据bean类型查找
 */
public class LookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(ResourcesPath.DEPENDENCY_LOOKUP_CONTEXT);

        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        lookupByType(beanFactory);

        lookupByAnnotation(beanFactory);
        lookupCollectionByType(beanFactory);

    }
    // TODO 这个的意义在哪？
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> bean = beanFactory.getBean(ObjectFactory.class);

        User user = bean.getObject();
        System.out.println("延迟查找 : " + user);
    }


    private static void lookupCollectionByType(BeanFactory beanFactory) {
        // 获取集合
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("获取所有的user : " + users);
        }
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        // 通过注解获取
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("获取所有的super : " + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        // 通过类型
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型获取 : " + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        // 通过名字获取
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找 : " + user);
    }
}
