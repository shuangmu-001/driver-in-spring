package org.thinking.in.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author zms
 * @date 5:33 下午 2021/2/2
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {
    // 1、实现 setAsText(String)方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 2、 将类型转换成 Properties
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        // 3、临时存储 properties
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();

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
