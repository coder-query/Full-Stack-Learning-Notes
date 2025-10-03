import com.it.pojo.User;
import com.it.service.Impl.Xml_Annotation_ServiceImpl;
import com.it.service.Xml_Annotation_Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/21 星期五 9:48
 */
public class Spring_Xml_Annotation_Test {
	public static void main(String[] args) {
		ApplicationContext IOC =
				new ClassPathXmlApplicationContext("test-spring-ioc.xml");

		User user = IOC.getBean(User.class);
		user.setAge(21);
		user.setGender("男");
		user.setName("帅宏-coding");

		Xml_Annotation_Service xmlAnnotationService = IOC.getBean(Xml_Annotation_Service.class);

		System.out.println(user);
		xmlAnnotationService.sayHello("帅宏-coding");
	}
}
