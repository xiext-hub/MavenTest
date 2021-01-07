package com.chuangtai.springboot;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post方法")
@RequestMapping(value = "/v1")  //指所有的地址前面都加上v1  如：http://localhost:8888/v1/login?username=tingzi&password=123456
public class MyPostMethod {

    //这个变量用来装cookie信息
    private static Cookie cookie;

    //用户登录成功后，获取到cookie后，访问其它用户列表接口
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，登录成功后获取到cookie", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {

        if (username.equals("tingzi") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";

        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                                @RequestBody UserBean u) {

        UserBean userBean;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookie信息是否合法
        for (Cookie c : cookies) {
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUsername().equals("tingzi")
                    && u.getPassword().equals("123456")) {
                userBean = new UserBean();
                userBean.setName("tingzi");
                userBean.setAge("28");
                userBean.setSex("女");
                return userBean.toString();
            }
        }
        return  "参数不合法";
    }

}
