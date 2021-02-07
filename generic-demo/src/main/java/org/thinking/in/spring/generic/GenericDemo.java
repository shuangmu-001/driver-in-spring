package org.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zms
 * @date 2:35 下午 2021/2/5
 */
public class GenericDemo {

    public static void main(String[] args) {
        // java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("wy");
        list.add("zms");
        // 编译错误
        // list.add(1);
        // 泛型擦写
        Collection temp = list;
        // 编译成功
        temp.add(1);
        System.out.println(list);
    }

}
