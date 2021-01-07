package com.chuangtai.springboot;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//在Application里面执行main方法，这个类里面所有的方法都会被执行  在浏览器地址栏输入地址：locaihost:8888/getCookies


@RestController
@Api(value = "/",description = "这是我全部的get方法") //对接口和实体类添加注释，生成swaggerui-doc
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookie值")//对接口和实体类添加注释，生成swaggerui-doc
    public String getCookie(HttpServletResponse response) {
        //HttpServletRequest  装请求信息的类
        //HttpServletResponse   装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你返回cookies信息成功";
    }


    /**
     * 要求客户端携带cookie信息访问  这是一个需要携带cookie信息才能访问的get请求
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/get/with/cookie", method = RequestMethod.GET)
    @ApiOperation(value = "客户端携带cookie信息访问")//对接口和实体类添加注释，生成swaggerui-doc
    public String getWithCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "这是一个需要携带cookie信息才能访问的请求。";
            }
        }

        return "你必须携带cookies信息来";
    }


    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url：ip:post/get/with/param?key=value&key=value
     * 如:http://localhost:8888/get/with/param?start=10&end=20
     */
    @RequestMapping(value = "get/with/param", method = RequestMethod.GET)
    @ApiOperation(value ="需要携带参数才能访问的get请求第一种实现方式")//对接口和实体类添加注释，生成swaggerui-doc
    public Map<String, Integer> shopList(@RequestParam Integer start
            , @RequestParam Integer end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋子", 200);
        myList.put("衬衫", 158);
        myList.put("羽绒服", 599);

        return myList;

    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第二种实现方式 url：ip:post/get/with/param/参数/参数
     * 如：http://localhost:8888/get/with/param/10/56
     */
    @RequestMapping(value = "get/with/param/{start}/{end}", method = RequestMethod.GET)
    @ApiOperation(value ="需要携带参数才能访问的get请求第二种实现方式")//对接口和实体类添加注释，生成swaggerui-doc
    public Map<String,Integer> myShopList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("耐克", 699);
        myList.put("阿迪达斯", 1299);
        myList.put("百里黑天鹅马丁靴", 1599);

        return myList;
    }

}
