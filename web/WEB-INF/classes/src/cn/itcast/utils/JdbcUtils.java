package cn.itcast.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(prop.getProperty("driver"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
    }


    public static void release(Connection conn, Statement st, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();   //throw new
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            st = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
