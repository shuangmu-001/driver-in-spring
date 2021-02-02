package org.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 懋小方
 *
 * @author zms
 * @date 11:39 上午 2021/1/28
 */
public class FileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(":", System.lineSeparator()));

        String currentJavaFile = System.getProperty("user.dir") + "/spring-resource-demo/src/main/java/org/thinking/in/spring/resource/FileSystemResourceDemo.java";
        File file = new File(currentJavaFile);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        Reader reader = encodedResource.getReader();
        System.out.println(IOUtils.toString(reader));
    }

}
