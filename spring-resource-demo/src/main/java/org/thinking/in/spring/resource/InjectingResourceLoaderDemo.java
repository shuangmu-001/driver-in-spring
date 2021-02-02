package org.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖注入 {@link org.springframework.core.io.ResourceLoader}对象
 *
 * @author zms
 * @date 3:46 下午 2021/1/28
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    private ResourceLoader awareResourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.awareResourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("awareResourceLoader == autowiredResourceLoader : " + (awareResourceLoader == autowiredResourceLoader));
        System.out.println("applicationContext == awareResourceLoader : " + (applicationContext == awareResourceLoader));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为 Configuration class
        context.register(InjectingResourceLoaderDemo.class);
        // 启动 Spring 应用上下问
        context.refresh();
        InjectingResourceLoaderDemo bean = context.getBean(InjectingResourceLoaderDemo.class);

        System.out.println(bean.awareResourceLoader == context);
        // 关闭 Spring 应用上下文
        context.stop();
    }

}
