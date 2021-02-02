package org.thinking.in.spring.ioc.domain;

/**
 * @author zms
 * @date 2:12 下午 2021/2/2
 */
public class Company {

    private String name;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
