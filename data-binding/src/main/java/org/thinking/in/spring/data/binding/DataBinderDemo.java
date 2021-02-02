package org.thinking.in.spring.data.binding;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.thinking.in.spring.ioc.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * @date 11:45 上午 2021/2/2
 */
public class DataBinderDemo {

    public static void main(String[] args) {
        User user = new User();
        // 1、创建DataBinder
        DataBinder dataBinder = new DataBinder(user, "");
        // 2、创建PropertyValues
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "wy");
        // PropertyValues 存在 User 中不存在的属性值
        // DataBinder 忽略未知属性
        map.put("sex", 'M');
        // PropertyValues 存在一个嵌套属性，比如 company.name
        // DataBinder 支持嵌套属性
        // TODO company谁创建？
        map.put("company.name", "axt");

        PropertyValues propertyValues = new MutablePropertyValues(map);
        // 调整 ignoreUnknownFields true（默认）-> false （报异常NotWritablePropertyException，sex不是user的属性）
        // dataBinder.setIgnoreUnknownFields(false);
        // 调整 autoGrowNestedPaths true（默认）-> false （报异常NullValueInNestedPathException，company没有创建）
        // dataBinder.setAutoGrowNestedPaths(false);
        // 调整 IgnoreInvalidFields false（默认）-> true （默认情况下调整不变化，需要调整 AutoGrowNestedPaths 为 false）
        // dataBinder.setIgnoreInvalidFields(true);

        // dataBinder.setRequiredFields("id", "name", "city");
        // PropertyValues 存在 User 中存在的属性值，但是不绑定
        dataBinder.setDisallowedFields("id", "name");
        dataBinder.bind(propertyValues);
        // 3、输出user对象
        System.out.println(user);

        // 4、绑定结果（结果包含错误文案code，不会跑出异常）
        BindingResult bindingResult = dataBinder.getBindingResult();

        System.out.println(bindingResult);
    }

}
