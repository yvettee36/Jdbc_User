package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.exception.DaoException;
import cn.itcast.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//2
public class UserDaoJdbcImpl implements UserDao {
    public void add(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into users(id,username,password,email,birthday,nickname) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getId());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setDate(5,new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(6,user.getNickname());

            int num = ps.executeUpdate();
            if (num < 1) {
                throw new RuntimeException("注册用户失败");
            }
        } catch (Exception e) {
            throw new DaoException(e);//gosling  thinking in java  spring
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
    }

    /*
     * Statement和PreparedStatement的区别
     * PreparedStatement是Statement的孩子
     * PreparedStatement可以防止sql注入的问题
     * PreparedStatement会对sql语句进行预编译，减轻服务器的压力
     */
    public User find(String username, String password) {

        Connection conn = null;
        PreparedStatement ps = null;//PreparedStatement预防sql注入
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where username=? and password =?";
            ps = conn.prepareStatement(sql);//预编译这条sql语句
            ps.setString(1,username);//数据库会对登录时拿到的内容进行转义
            ps.setString(2,password);

            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setBirthday(rs.getDate("birthday"));
                user.setNickname(rs.getString("nickname"));
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
    }

    //查找注册的用户是否在数据库中存在
    public boolean find(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
    }
}
