package org.thinking.in.spring.resource.spring;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author zms
 * @date 5:12 下午 2021/1/28
 */
public class Handler extends sun.net.www.protocol.zms.Handler {

    public static void main(String[] args) throws IOException {
        URL.setURLStreamHandlerFactory(new SpringURLStreamHandlerFactory());
        URL url = new URL("spring:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));

    }

}
