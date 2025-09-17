package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 一对多  一个老师对应多个学生
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student_02 {
    private String name;
    private String sex;
}
