package com.it.mapper;

import com.it.pojo.Student;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public class StudentMapperTest {
    // 单个参数
    @Test
    public void testSelect_01() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.getStudentById(1);
        System.out.println(student);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /// 多个参数
    @Test
    public void testUpdate() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            int flag = studentMapper.updateStudentByIdAndNameAndAge(1, "小张", 100);
            System.out.println(flag);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /// 实体参数
    @Test
    public void testInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student = new Student();
            student.setStuName("老七");
            student.setStuAge(22);
            student.setStuClazz("四班");
            int flag = studentMapper.insertStudent(student);
            System.out.println(flag);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    /// Map类型参数
    @Test
    public void testSelect_02() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, String> map = new HashMap<>();
        map.put("map_Name", "宏图");
        map.put("map_Clazz", "三班");
        Student student = studentMapper.getStudentByNameAndClazz(map);
        System.out.println(student);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /// 返回结构类型为Integer
    @Test
    public void testSelect_03() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Integer count = studentMapper.selectStudentCount();
        System.out.println("当前表有" + count + "个学生");
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /// 返回类型为实体类型
    @Test
    public void testSelect_04() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectStudentByName("张三");
        System.out.println(student);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /// 返回Map集合类型
    @Test
    public void testSelect_05() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> studentAvgAge = studentMapper.selectStudentAvgAge();
        Set<String> keySet = studentAvgAge.keySet();
        for (String key : keySet) {
            System.out.println(key + " : " + studentAvgAge.get(key));
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /// 返回List集合类型
    @Test
    public void testSelect_06() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.selectAllStudent();
        for (Student student : studentList) {
            System.out.println(student);
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /**
     * 根据输入的名字stuName进行模糊匹配,返回list封装的数据
     */
    @Test
    public void testGetAllUsersByLikeName() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.getAllUsersByLikeName("帅");
        studentList.stream().forEach(System.out::println);
    }

}
