package org.thinking.in.spring.ioc.dependency.injection.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zms
 * @date 12:23 下午 2020/12/16
 */
@Service
public class D {

    C c;

    @Autowired
    public void cH(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }
}
