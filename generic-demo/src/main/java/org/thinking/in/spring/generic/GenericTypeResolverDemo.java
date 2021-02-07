package org.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 2:18 下午 2021/2/7
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("---------常规类型作为方法返回值------------");
        // String 是 Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        System.out.println("---------方法返回值的类型的泛型不具体化------------");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getArrayList");
        System.out.println("---------方法返回值的类型的泛型具体化------------");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

    }

    public static String getString() {
        return null;
    }

    public static <T> List<T> getArrayList() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    public static T getT() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class clazz, Class genericIfc, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = clazz.getMethod(methodName, parameterTypes);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, clazz);
        // 常规类型作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, clazz.getSimpleName(), returnType);
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        // 返回类型的泛型参数
        // 1、常规类型作为方法返回值为 null
        // 2、方法返回值的类型的泛型不具体化 null
        // 3、方法返回值的类型的泛型具体化(边界，字节码是有记录的)  具体化的类型
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, genericIfc.getSimpleName(), returnTypeArgument);
    }

}

class T {

}
