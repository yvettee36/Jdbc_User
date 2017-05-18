package junit.test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoJdbcImpl;
import cn.itcast.domain.User;
import org.junit.Test;

import java.util.Date;

//4
public class UserDaoJdbcTest {
    @Test
    public void testAdd() {
        User user = new User();
        user.setBirthday(new Date());
        user.setEmail("98@sina.com");
        user.setId("34567");
        user.setNickname("啊数子");
        user.setPassword("123");
        user.setUsername("qwe");

        UserDao dao = new UserDaoJdbcImpl();
        dao.add(user);
    }

    @Test
    public void testFind() {
        UserDao dao = new UserDaoJdbcImpl();
        User user = dao.find("aaa", "123");
        System.out.println(user);
    }

    @Test
    public void testFindByUsername() {
        UserDao dao = new UserDaoJdbcImpl();
        boolean b = dao.find("aaa");
    }
}
