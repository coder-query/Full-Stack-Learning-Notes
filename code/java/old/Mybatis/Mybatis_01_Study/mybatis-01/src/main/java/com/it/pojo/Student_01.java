package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 多对一  多个学生对应一个老师
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student_01 {
    private Integer id;
    private String name;
    private String sex;
    private Teacher_01 teacher;
}
