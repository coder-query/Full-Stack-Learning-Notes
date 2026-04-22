package org.shuai.controller.redismq;

import org.shuai.controller.redismq.base.ListVer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    private ListVer listVer;


    /**
     * list
     */
    @RequestMapping("/list")
    public String list() {
        return "list";
    }
}
