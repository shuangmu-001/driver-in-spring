package org.thinking.in.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * @author zms
 * @date 5:25 下午 2021/2/4
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType()) &&
                String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties) source;

        StringBuilder stringBuilder = new StringBuilder();

        properties.forEach((key, value) ->
                stringBuilder.append(key)
                        .append("=")
                        .append(value)
                        .append(System.getProperty("line.separator"))
        );
        return stringBuilder.toString();
    }

}
