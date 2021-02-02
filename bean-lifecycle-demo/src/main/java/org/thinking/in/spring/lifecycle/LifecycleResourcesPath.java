package org.thinking.in.spring.lifecycle;

import org.thinking.in.spring.ioc.utils.ResourcesPath;

/**
 * @author zms
 * @date 2:29 下午 2021/1/26
 */
public interface LifecycleResourcesPath extends ResourcesPath {

    String CONSTRUCTOR_DEPENDENCY_INJECTION = "META-INF/bean-constructor-dependency-injection.xml";

    String USER_PROPERTIES = "META-INF/user.properties";

}
