package org.shuai.boot_mongodb_code.repository;

import org.shuai.boot_mongodb_code.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 员工Dao 定义Dao接口继承MongoRepository<实体类型,主键类型>
 *
 * @author zrj
 * @since 2022/3/29
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {}
