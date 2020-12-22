package com.tingzi.util;




import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class TestDemo1 {


    @BeforeTest
    public void Before(){
        System.out.println("测试之前");
    }


    private static final String URL = "http://120.26.54.124:8188";

@Test
    public void TestPos_Login(){
    String url = URL + "/spms/services/login";
    Map<String, Object> requestParam = new HashMap<String, Object>();
    requestParam.put("cityCode", "330111");
    requestParam.put("meid", "e021a2e666f03bb5");
    requestParam.put("userPwd", "123456");
    requestParam.put("userName", "xxt");
    Map<String, Object> resultMap = HttpClientUtil.doPost(url, GsonUtil.objectToJson(requestParam));
    }


    @AfterTest
    public void After(){
        System.out.println("测试结束");
    }


//    @Test
//    public void TestSpms_Login(){
//        String url = URL + "/spms/checkuser.do ";
//        Map<String, Object> requestParam = new HashMap<String, Object>();
//        requestParam.put("cityCode", "0");
//        requestParam.put("account", "xxt");
//        requestParam.put("password", "Kzz101369...");
//        Map<String, Object> resultMap = HttpClientUtil.doPost(url, GsonUtil.objectToJson(requestParam));
//    }





}
