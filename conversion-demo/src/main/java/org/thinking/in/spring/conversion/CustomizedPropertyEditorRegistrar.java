package org.thinking.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.thinking.in.spring.ioc.domain.User;

/**
 * @author zms
 * @date 5:34 下午 2021/2/3
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    // TODO propertyPath的作用是什么
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
//        registry.registerCustomEditor(User.class, new StringToPropertiesPropertyEditor());
        registry.registerCustomEditor(User.class, "hello", new StringToPropertiesPropertyEditor());
    }
}
