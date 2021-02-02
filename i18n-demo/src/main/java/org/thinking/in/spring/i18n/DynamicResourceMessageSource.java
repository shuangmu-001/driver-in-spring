package org.thinking.in.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * 动态更新 {@link MessageSource}资源
 * <p>
 * 实现步骤
 * <p>
 * 1、定位资源位置（Properties文件）
 * 2、初始化 Properties 对象
 *
 * @author zms
 * @date 4:28 下午 2021/1/29
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourcePath = "/META-INF/msg.properties";

    private final Properties properties;

    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        this.properties = loadMessageProperties();
    }

    private Properties loadMessageProperties() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        Properties properties = new Properties();
        try (Reader reader = encodedResource.getReader()) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        String messageFormatPattern = properties.getProperty(s);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader != null ? resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        String name = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.println(name);
    }
}
