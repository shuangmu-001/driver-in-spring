package org.thinking.in.spring.ioc.dependency.injection.constructor;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

/**
 * @author zms
 * @date 12:24 下午 2020/12/16
 */
@Service
public class F {

    ObjectFactory<E> eObjectFactory;

    public F(ObjectFactory<E> eObjectFactory) {
        this.eObjectFactory = eObjectFactory;
    }

    public void getE() {
        System.out.println(eObjectFactory.getObject());
    }
}
