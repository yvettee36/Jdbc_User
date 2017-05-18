package cn.itcast.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.WebUtils;
import cn.itcast.web.formbean.RegisterForm;

//处理注册请求
public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //1.合法性校验  formBean把表单数据封装到formbean
        RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
        boolean b = form.validate();
        //2.如果校验失败，跳回到表单页面，回显失败消息
        if (!b) {
            request.setAttribute("form", form);
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            return;
        }

        //3.校验成功，则调用service处理注册请求
        BusinessServiceImpl service = new BusinessServiceImpl();

        User user = new User();
        WebUtils.copyBean(form, user);
        user.setId(WebUtils.generateID());
        try {
            service.register(user);
            //6.service处理成功，跳转到网站的全局消息显示页面，为用户显示注册成功消息
            request.setAttribute("message", "注册成功");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } catch (UserExistException e) {
            //4.service处理不成功，原因是用户存在跳回注册页面显示注册用户已存在
            form.getErrors().put("username", "注册的用户已存在");
            request.setAttribute("form", form);
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            //5.service处理不成功，原因是其他问题，跳转到全局消息处理界面，显示友好错误页面
            e.printStackTrace();
            request.setAttribute("message", "服务器出现未知错误");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
