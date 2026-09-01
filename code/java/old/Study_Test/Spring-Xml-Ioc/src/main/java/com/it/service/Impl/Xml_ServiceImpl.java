package com.it.service.Impl;

import com.it.service.Xml_Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * @author 帅宏-coding @Money java_offer_13k
 * @date 2025/3/21 星期五 9:44
 */
public class Xml_ServiceImpl implements Xml_Service {

  @Override
  public void sayHello(String name) {
    System.out.println("Hello 靓仔 ---> " + name);
  }
}
