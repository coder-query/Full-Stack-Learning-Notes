import com.it.pojo.User;
import com.it.service.Impl.Xml_ServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:40
 */
public class Spring_Xml_Test {
	public static void main(String[] args) {
		ApplicationContext IOC =
				new ClassPathXmlApplicationContext("test-spring-ioc.xml");

		User user = IOC.getBean(User.class);
		Xml_ServiceImpl xmlService = IOC.getBean(Xml_ServiceImpl.class);

		System.out.println(user);
		xmlService.sayHello("帅宏-coding");

	}
}
