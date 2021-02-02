package org.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 懋小方
 *
 * @author zms
 * @date 11:39 上午 2021/1/28
 */
public class FileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFile = "/" + System.getProperty("user.dir") + "/spring-resource-demo/src/main/java/org/thinking/in/spring/resource/FileSystemResourceDemo.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(currentJavaFile);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        Reader reader = encodedResource.getReader();
        System.out.println(IOUtils.toString(reader));
    }

}
