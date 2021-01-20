package com.tingzi.test;

import com.tingzi.util.RestClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author xiexiaoting
 * @Date 2021/1/14 15:01
 * @Version 1.0
 */
public class Spms_FormPostWithCookie {
    private String url;
    private ResourceBundle bundle;


    @BeforeTest
    public void BeforeTest() {        //在测试之前，读取resource里面application.properties 里面的地址信息
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookie() throws IOException {
        String methodurl = bundle.getString("testGetCookie.url");
        String testurl = this.url + methodurl; //拼接URL

        //设置参数  post-form表单提交
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("cityCode", "0");
        requestParam.put("account", "xiext");
        requestParam.put("password", "Xie816...");


         }
    }
