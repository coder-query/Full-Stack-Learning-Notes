package com.it.service.Impl;

import com.it.service.Xml_Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:44
 */
public  class Xml_ServiceImpl implements Xml_Service {

	String

    @Override
    public void sayHello(String name) {
        System.out.println("Hello 靓仔 ---> " + name);
    }
}
