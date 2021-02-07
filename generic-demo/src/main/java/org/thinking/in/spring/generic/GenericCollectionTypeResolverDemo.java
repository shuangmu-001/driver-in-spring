package org.thinking.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 2:18 下午 2021/2/7
 */
public class GenericCollectionTypeResolverDemo {

    private static StringList stringList;

    private static List<String> strings;

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        // getCollectionType 返回具体化泛型参数类型集合成员类型 = java.lang.String
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
    }

}
