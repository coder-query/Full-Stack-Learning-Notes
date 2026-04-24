package com.shuai.model;


import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table(value = "t_test")
@Data
public class Test {
    private String name;
    private Integer age;
    private String sex;
    private String address;
}
