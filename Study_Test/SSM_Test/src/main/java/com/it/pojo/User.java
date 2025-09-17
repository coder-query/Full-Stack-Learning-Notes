package com.it.pojo;

import com.it.service.Impl.ItHelloServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/19 星期三 11:18
 */

@Data
@AllArgsConstructor
/// 有参
@NoArgsConstructor
/// 无参
@Component
public class User {
	private int age;
	private String name;
	private String gender;
}
