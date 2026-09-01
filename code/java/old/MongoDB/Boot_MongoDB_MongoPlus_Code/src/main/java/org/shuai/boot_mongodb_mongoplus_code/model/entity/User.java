package org.shuai.boot_mongodb_mongoplus_code.model.entity;

import com.mongoplus.annotation.ID;
import lombok.Data;

@Data
public class User {
  @ID // 使用ID注解，标注此字段为MongoDB的_id，或者继承BaseModelID类
  private String id;
  private String name;
  private Long age;
  private String email;
}
