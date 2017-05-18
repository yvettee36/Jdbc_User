package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.service.impl.BusinessServiceImpl;

//9
public class ServiceTest {
    @Test
    public void testRegister() {
        User user = new User();
        user.setBirthday(new Date());
        user.setEmail("kjhu65esx@sina.com");
        user.setId("8764235");
        user.setNickname("大胜靠德");
        user.setPassword("9876");
        user.setUsername("liuyt");
        BusinessServiceImpl service = new BusinessServiceImpl();
        try {
            service.register(user);
            System.out.println("注册成功。");
        } catch (UserExistException e) {
            System.out.println("用户已存在。");
        }

    }

    @Test
    public void testLogin() {
        BusinessServiceImpl service = new BusinessServiceImpl();
        User user = service.login("gggg", "123");
        System.out.println(user);
    }
}
