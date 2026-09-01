package com.shuai.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/7/31 003
 */
@Table(value = "user")
@Data
@FieldNameConstants
public class User {
  @Id(keyType = KeyType.Auto)
  private Integer id;

  private String  name;

  private String userAccount;

  private String userPassword;
}
