package com.shuai.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/7/31 003
 */
@Table("tb_account")
@Data
public class Account {
  @Id(keyType = KeyType.Auto)
  private Integer id;

  private String account;

  private String password;
}
