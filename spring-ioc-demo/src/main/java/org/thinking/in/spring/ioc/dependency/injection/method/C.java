package org.thinking.in.spring.ioc.dependency.injection.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zms
 * @date 12:22 下午 2020/12/16
 */
@Service
public class C {

    D d;

    @Autowired
    public void dH(D d) {
        this.d = d;
    }

}
