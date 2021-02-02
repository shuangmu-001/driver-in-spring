package org.thinking.in.spring.ioc.dependency.injection.constructor;

import org.springframework.stereotype.Service;

/**
 * @author zms
 * @date 12:24 下午 2020/12/16
 */
@Service
public class E {

    F f;

    public E(F f) {
        this.f = f;
    }
}
