package cn.itcast.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private Properties prop = new Properties();

    private DaoFactory() {
        InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    /*如果将来产生多个dao，就不能写死(泛型)
     *比如当调用createDao方法时，会传一个,UserDao的接口，
     * 然后根据名称解读配置文件dao.properties加载UserDao的实现类，new出对象返回
     */
    public <T> T createDao(Class<T> clazz) {

        String name = clazz.getSimpleName();//得到接口名称
        String className = prop.getProperty(name);
        try {
           T dao = (T) Class.forName(className).newInstance();
           return dao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
