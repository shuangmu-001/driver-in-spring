package org.thinking.in.spring.ioc.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wcl
 * @date 10:59 上午 2020/6/9
 */
//@Component
public class User implements BeanNameAware {

    private Long id;

    private String name;

    private Integer age;

    private Company company;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", company=" + company +
                ", scores=" + Arrays.toString(scores) +
                ", cities=" + Arrays.toString(cities) +
                ", resource=" + resource +
                ", map=" + map +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private int[] scores;

    private City[] cities;

    @Value("classpath:/META-INF/dependency-lookup-context.xml")
    private Resource resource;

    //    @Value("#{${test.maps}}")
    private Map<String, String> map;

    private transient String beanName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public static User createUser() {
        User user = new User();
        user.setAge(18);
        user.setName("王岩");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println("用户对象" + beanName + "初始化。。。。");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("用户对象" + beanName + "销毁。。。。");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
