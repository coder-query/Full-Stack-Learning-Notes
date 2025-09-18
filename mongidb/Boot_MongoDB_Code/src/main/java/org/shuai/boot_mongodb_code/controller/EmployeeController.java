package org.shuai.boot_mongodb_code.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.shuai.boot_mongodb_code.model.reponse.Response;
import org.shuai.boot_mongodb_code.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工控制器
 *
 * @author zrj
 * @since 2022/3/29
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
  @Resource private EmployeeService employeeService;

  /** 新增接口 */
  @GetMapping("/create")
  public Response create() {
    return Response.success(employeeService.create());
  }

  /** 更新接口 */
  @GetMapping("/update")
  public Response update() {
    return Response.success(employeeService.update());
  }

  /** 删除接口 */
  @GetMapping("/delete")
  public Response delete() {
    return Response.success(employeeService.delete());
  }

  /** 查询接口 */
  @GetMapping("/select")
  public Response select() {
    return Response.success(employeeService.select());
  }
}
