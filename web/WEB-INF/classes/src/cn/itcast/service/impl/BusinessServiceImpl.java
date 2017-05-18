package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.factory.DaoFactory;
import cn.itcast.utils.ServiceUtils;

//6  对web层提供所有的业务服务
public class BusinessServiceImpl {

    /*
     *现在这个业务类的所有代码没有一行依赖具体的实现
     * 现在即使把UserDao实现删除，业务层也不会报错
     */
    private UserDao dao = DaoFactory.getInstance().createDao(UserDao.class);

    //对web层提供注册服务
    public void register(User user) throws UserExistException {
        boolean b = dao.find(user.getUsername());
        if (b) {
            throw new UserExistException("用户已经存在！");
            //用户存在，给web层抛出编译时异常
        } else {
            user.setPassword(ServiceUtils.md5(user.getPassword()));
            dao.add(user);
        }
    }

    //对web层提供登录服务
    public User login(String username, String password) {
        password = ServiceUtils.md5(password);
        return dao.find(username, password);
    }
}
