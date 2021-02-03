package org.thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * @author zms
 * @date 5:32 下午 2021/2/2
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        String text = "name = wy";

        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

    }

}
