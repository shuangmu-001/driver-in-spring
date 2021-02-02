package org.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.thinking.in.spring.resource.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 依赖注入 {@link Resource}对象
 *
 * @author zms
 * @date 3:46 下午 2021/1/28
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResources;

    @Value("${user.dir}")
    private String currentProjectRootPath;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("====================");
        Stream.of(propertiesResources)
                .map(ResourceUtils::getContent)
                .forEach(System.out::println);
        System.out.println("====================");
        System.out.println(currentProjectRootPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类为 Configuration class
        context.register(InjectingResourceDemo.class);
        // 启动 Spring 应用上下问
        context.refresh();
        // 关闭 Spring 应用上下文
        context.stop();
    }

}
