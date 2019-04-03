/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package: com.mhc.magotan.core.integration
 * @author: 伊利 (yili@maihaoche.com)
 * @date: 2018/8/28
 * @Copyright: 2017-2018 www.maihaoche.com Inc. All rights reserved.
 * 注意: 本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.windy.client;

import com.windy.api.HelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceAdapter {

    @Autowired
    private HelloFacade helloFacade;

    public String hello(String parm) {

        return helloFacade.hello(parm);
    }

}
