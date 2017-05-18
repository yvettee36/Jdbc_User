package cn.itcast.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BusinessServiceImpl service = new BusinessServiceImpl();
        User user = service.login(username, password);
        if (user != null) {
            //在session里存入一个登录标记
            request.getSession().setAttribute("user", user);
            //用户登录成功，跳转到首页
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        request.setAttribute("message", "用户名或密码错误");
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
