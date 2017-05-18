package cn.itcast.web.formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterForm {
    private String username;
    private String password;
    private String password2;
    private String email;
    private String birthday;
    private String nickname;
    private Map errors = new HashMap();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Map getErrors() {
        return errors;
    }

    public void setErrors(Map errors) {
        this.errors = errors;
    }

    public boolean validate() {
        boolean isOk = true;
        //用户名不能为空，且3-8位字母
        //密码不空，3-8位数字
        //邮箱不空，且一个合法邮箱
        //生日可以为空，如果不为空，则要合法
        //昵称要汉字，不能为空

        if (username == null || username.trim().equals("")) {
            isOk = false;
            errors.put("username", "用户名不能为空");
        } else {
            if (!username.matches("[A-Za-z]{3,8}")) {
                isOk = false;
                errors.put("username", "用户名3-8位字母");
            }
        }

        if (password == null || password.trim().equals("")) {
            isOk = false;
            errors.put("password", "密码不能为空");
        } else {
            if (!password.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("password", "密码3-8位数字");
            }
        }

        if (password2 == null || password2.trim().equals("")) {
            isOk = false;
            errors.put("password2", "确认密码不能为空");
        } else {
            if (!password.equals(password2)) {
                isOk = false;
                errors.put("password2", "两次输入密码不一致");
            }
        }
        if (email == null || email.trim().equals("")) {
            isOk = false;
            errors.put("email", "邮箱不能为空");
        } else {//aa@sina.com  aa@sina.com.cn
            if (!email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱格式不合法");
            }
        }
        if (birthday != null && !birthday.trim().equals("")) {
            DateLocaleConverter dlc = new DateLocaleConverter();
            try {
                dlc.convert(birthday, "yyyy-MM-dd");
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "日期格式不对");
            }
            //SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        }
        if (nickname == null || nickname.trim().equals("")) {
            isOk = false;
            errors.put("nickname", "昵称不能为空");
        } else {//汉字区间  \u4e00-\u9fa5
            if (!nickname.matches("^([\u4e00-\u9fa5]+)$")) {
                isOk = false;
                errors.put("nickname", "昵称必须为汉字");
            }
        }
        return isOk;
    }
}
