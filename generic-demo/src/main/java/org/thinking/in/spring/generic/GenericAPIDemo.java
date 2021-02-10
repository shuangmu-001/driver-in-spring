package org.thinking.in.spring.generic;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zms
 * @date 2:48 下午 2021/2/5
 */
public class GenericAPIDemo {

    public static void main(String[] args) {
        // 原声类型 primitive types : int,long
        Class intClass = int.class;
        // 数组类型 array types : int[],Object[]
        Class objectArrayClass = Object[].class;
        // 每个元素的类型 -> java.lang.Object
        System.out.println("数组类型的组件");
        System.out.println(objectArrayClass.getComponentType());
        // 原始类型 raw types : java.lang.String
        Class rawClass = String.class;
        // 泛型参数类型 parameterized type
        // 获取泛型接口会将所有接口都返回
        System.out.println("class.getGenericInterfaces()");

        Type[] genericInterfaces1 = ArrayList.class.getGenericInterfaces();
        Stream.of(genericInterfaces1)
                .forEach(type -> System.out.println(type.getClass().getSimpleName() + " : " + type));
        // 获取泛型父类，
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println("最近泛型父类 : class.getGenericSuperclass() = " + ArrayList.class.getGenericSuperclass().getClass());
        // 泛型具体化
        Type genericSuperclass = StringList.class.getGenericSuperclass();
        System.out.println(genericSuperclass);
        // 泛型不具体化
        genericSuperclass = M.class.getGenericSuperclass();
        System.out.println(genericSuperclass);
        genericSuperclass = C.class.getGenericSuperclass();
        System.out.println(genericSuperclass);
        // 如果当前类没有泛型

        // parameterizedType.getRawType() = java.util.AbstractList
        // 泛型类型变量 Type variable :

        System.out.println("泛型的具体类型 : ");

        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        Stream.of(typeArguments)
                .forEach(type -> System.out.println(type.getClass().getSimpleName() + " : " + type));
        System.out.println();
        parameterizedType = (ParameterizedType) C.class.getGenericSuperclass();
        typeArguments = parameterizedType.getActualTypeArguments();

        Stream.of(typeArguments)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);
        System.out.println();

        TypeVariable[] typeParameters = A.class.getTypeParameters();
        Stream.of(typeParameters)
                .forEach(System.out::println);
        System.out.println();
        Stream.of(A.class.getGenericInterfaces())
                .forEach(System.out::println);
        Class<? extends Type> aClass = C.class.getGenericSuperclass().getClass();
        System.out.println("genericSuperClass class : " + aClass);
        System.out.println("genericSuperClass object typeName: " + C.class.getGenericSuperclass().getTypeName());
        System.out.println("genericSuperClass object ownerType: " + ((ParameterizedType) C.class.getGenericSuperclass()).getOwnerType());
        System.out.println("genericSuperClass object rawType: " + ((ParameterizedType) C.class.getGenericSuperclass()).getRawType());
        System.out.println("genericSuperClass object ActualTypeArguments: " + Arrays.toString(((ParameterizedType) C.class.getGenericSuperclass()).getActualTypeArguments()));
        typeParameters = aClass.getTypeParameters();
        Stream.of(typeParameters)
                .forEach(type -> System.out.println(type.getClass().getSimpleName() + " : " + type));
        System.out.println();


        System.out.println(Arrays.toString(L.class.getTypeParameters()));
        // TypeVariable -> class.getTypeParameters()
        ParameterizedTypeImpl make = ParameterizedTypeImpl.make(D.class, new Type[]{}, null);
        System.out.println(Arrays.toString(make.getActualTypeArguments()));
        System.out.println(make.getTypeName());
        System.out.println(make.getRawType());
        System.out.println(make.getOwnerType());

    }

    interface A<E> {

    }

    interface H {

    }


    interface D extends H {

    }

    static class B<T, C> {

    }

    static class C<E, F> extends B<E, F> implements A<E>, D {

    }

    class M extends B {

    }

    class N extends M {

    }

    class L extends N {

    }

}
