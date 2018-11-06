package baiyang.blockchain.junit.test;

import baiyang.blockchain.domain.User;
import baiyang.blockchain.exception.UserExistException;
import baiyang.blockchain.service.impl.BusinessServiceImpl;
import org.junit.Test;

import java.util.Date;

public class ServiceTest {
	
	@Test
	public void testRegister() {
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("baibai@163.com");
		user.setId("233333");
		user.setNikename("��ɵ��");
		user.setPassword("123");
		user.setUsername("bbb");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		try {
			service.register(user);
			System.out.println("ע��ɹ�����");
		} catch (UserExistException e) {
			System.out.println("�û��Ѵ���");
		}
		
	}
	
	@Test
	public void testLogin() {
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login("aaaa", "123");
		System.out.println(user);
	}

	@Test
	public void testAddNewBolck() {
		BusinessServiceImpl service = new BusinessServiceImpl();

		System.out.println(service.addNewBolck(10,"1"));
	}
}
