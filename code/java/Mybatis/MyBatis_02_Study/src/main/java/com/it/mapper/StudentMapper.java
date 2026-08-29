package com.it.mapper;

import com.it.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public interface StudentMapper {
    /**
     * 根据输入参数的个数和类型写案例
     */
    /// 单个参数
    Student getStudentById(@Param("stuId") Integer id);

    /// 多个参数
    int updateStudentByIdAndNameAndAge(@Param("stuId") Integer id, @Param("stuName") String name, @Param("stuAge") Integer age);

    /// 实体参数
    int insertStudent(Student student);

    /// Map类型参数
    Student getStudentByNameAndClazz(Map<String, String> stuMap);


    /**
     * 根据输出返回的数据类型写案例
     */
    /// 返回基本类型或者包装类型
    /// int ---> _int
    /// Integer ---> integer
    Integer selectStudentCount();

    /// 返回实体类对象
    Student selectStudentByName(@Param("name") String name);

    /// 返回Map集合类型
    Map<String, Object> selectStudentAvgAge();

    /// 返回List集合类型
    List<Student> selectAllStudent();


    /**
     * 根布名字模糊查询
     */

    List<Student> getAllUsersByLikeName(@Param("stuName") String stuName);
}

