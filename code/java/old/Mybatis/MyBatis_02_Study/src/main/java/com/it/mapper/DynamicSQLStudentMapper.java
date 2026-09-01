package com.it.mapper;

import com.it.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public interface DynamicSQLStudentMapper {
    /// if + where 标签
    Student selectStudentByIdAndName(@Param("stuId") Integer id, @Param("stuName") String name);

    /// set 标签
    int updateStudentById(Student student);


    /**
     * foreach 标签 ---> 根据id批量查询
     */
    List<Student> selectBatchStudentByIds(@Param("ids") List<Integer> ids);

    /**
     * foreach 标签 ---> 根据id批量删除
     */
    int deleteBatchStudentByIds(@Param("ids") List<Integer> ids);

    /**
     * foreach 标签 ---> 批量新增
     */
    int insertBatchStudent(@Param("studentList") List<Student> students);

    /**
     * foreach 标签 ---> 根据id批量修改姓名
     */
    int updateBatchStudent(@Param("studentList") List<Student> students);

}
