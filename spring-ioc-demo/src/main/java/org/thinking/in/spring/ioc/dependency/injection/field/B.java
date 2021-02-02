package org.thinking.in.spring.ioc.dependency.injection.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zms
 * @date 12:16 下午 2020/12/16
 */
@Service
public class B {
    @Autowired
    A a;

}
