package org.shuai.boot_mongodb_code.service;

import java.util.List;
import org.shuai.boot_mongodb_code.model.entity.Employee;

/**
 * @author zrj
 * @since 2022/3/29
 */
public interface EmployeeService {
  /**
   * 新增
   *
   * @return String
   */
  String create();

  /**
   * 更新
   *
   * @return String
   */
  String update();

  /**
   * 删除
   *
   * @return String
   */
  String delete();

  /**
   * 查询
   *
   * @return String
   */
  List<Employee> select();
}
