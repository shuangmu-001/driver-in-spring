package org.thinking.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author zms
 * @date 3:58 下午 2021/1/28
 */
public interface ResourceUtils {

    static String getContent(Resource resource) {
        try {
            return getContent(resource, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getContent(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        // 字符输入流
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }
}
