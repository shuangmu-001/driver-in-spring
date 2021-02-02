package org.thinking.in.spring.resource.spring;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * @author zms
 * @date 5:29 下午 2021/1/28
 */
public class SpringURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private static final String DEFAULT_PROTOCOL_NAME = "spring";

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (DEFAULT_PROTOCOL_NAME.equals(protocol)) {
            return new Handler();
        }

        String PREFIX = "sun.net.www.protocol";
        String className = PREFIX + "." + protocol + ".Handler";

        try {
            Class var3 = Class.forName(className);
            return (URLStreamHandler) var3.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InternalError("could not load " + protocol + "system protocol handler", e);
        }
    }
}
