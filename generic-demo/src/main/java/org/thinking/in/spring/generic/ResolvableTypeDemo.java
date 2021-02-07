package org.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zms
 * @date 3:34 下午 2021/2/7
 */
public class ResolvableTypeDemo<E> {

    private E e;

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        Field field = ResolvableTypeDemo.class.getDeclaredField("e");
        resolvableType = ResolvableType.forField(field);
        System.out.println("----------field-----------");
        System.out.println("generics : " + Arrays.toString(resolvableType.getGenerics()));
        System.out.println("type : " + resolvableType.getType());
        System.out.println("type class : " + resolvableType.getType().getClass());
        System.out.println("superType : " + resolvableType.getSuperType());
        System.out.println("source : " + resolvableType.getSource());
        System.out.println("rawClass :" + resolvableType.getRawClass());
        System.out.println("nested : " + resolvableType.getNested(1));
        System.out.println("----------method-----------");
        Method test = ResolvableTypeDemo.class.getDeclaredMethod("test", Object.class);
        resolvableType = ResolvableType.forMethodParameter(test, 0);
        System.out.println("generics : " + Arrays.toString(resolvableType.getGenerics()));
        System.out.println("type : " + resolvableType.getType());
        System.out.println("type class : " + resolvableType.getType().getClass());
        System.out.println("superType : " + resolvableType.getSuperType());
        System.out.println("source : " + resolvableType.getSource());
        System.out.println("rawClass :" + resolvableType.getRawClass());
        System.out.println("nested : " + resolvableType.getNested(1));

    }

    public void test(E e) {

    }

}
