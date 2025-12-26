package org.example.bootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_student")
@Data
public class Student {
    @TableField(value = "s_id")
    private Integer id;
    @TableField(value = "s_name")
    private String name;
    @TableField(value = "s_birth")
    LocalDateTime birth;
    @TableField(value = "s_sex")
    private String sex;
}
