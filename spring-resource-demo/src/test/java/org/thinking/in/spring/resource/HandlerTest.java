package org.thinking.in.spring.resource;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author zms
 * @date 5:07 下午 2021/1/28
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("zms:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }

}
