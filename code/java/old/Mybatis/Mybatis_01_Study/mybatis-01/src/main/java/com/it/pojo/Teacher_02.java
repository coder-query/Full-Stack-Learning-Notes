package com.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/*
* 一对多  一个老师对应多个学生
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher_02 {
    private Integer id;
    private String name;
    private List<Student_02> students;
}
