package com.windy.client;

import com.windy.api.HelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Controller
public class HelloClientController {
    @Autowired
    private HelloServiceAdapter helloFacade;

    @RequestMapping("/")
    @ResponseBody
    public String hello(@RequestParam  String parm) {

        return helloFacade.hello(parm);
    }
}
