package org.thinking.in.spring.ioc.domain;

import org.springframework.beans.factory.InitializingBean;
import org.thinking.in.spring.ioc.annotation.Super;

import javax.annotation.PostConstruct;

/**
 * @author wcl
 * @date 11:11 上午 2020/6/9
 */
@Super
public class SuperUser extends User implements InitializingBean {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                super.toString() +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SuperUser 的 afterPropertiesSet方法");
    }

    public void initMethod() {
        System.out.println("SuperUser 的 initMethod方法");
    }
}
