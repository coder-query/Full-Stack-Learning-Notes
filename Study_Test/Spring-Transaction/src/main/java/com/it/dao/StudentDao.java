package com.it.dao;

import com.it.pojo.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StudentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public void updateNameById(String name, Integer id) {
		String sql = "update test_students_tx set stu_name = ? where stu_id = ? ;";
		int rows = jdbcTemplate.update(sql, name, id);
	}

	public void updateAgeById(Integer age, Integer id) {
		String sql = "update test_students_tx set stu_age = ? where stu_id = ? ;";
		jdbcTemplate.update(sql, age, id);
	}

	public Student getStudentById(Integer id) {
		String sql = "select * from test_students_tx where stu_id = ? ;";
		BeanPropertyRowMapper<Student> rMapper = new BeanPropertyRowMapper<>(Student.class);
		return jdbcTemplate.queryForObject(sql, rMapper, id);
	}

	public List<Student> getAllStudents() {
		String sql = "select * from test_students_tx;";
		BeanPropertyRowMapper<Student> rMapper = new BeanPropertyRowMapper<>(Student.class);
		return jdbcTemplate.query(sql, rMapper);
	}
}
