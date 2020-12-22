package com.tingzi.test;

import com.tingzi.util.GsonUtil;
import com.tingzi.util.HttpClientUtil;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.HashMap;

import java.util.Map;




class TestDemo2 {

    int responseCode;
    private static final String URL = "http://120.26.54.124:8188";

    @BeforeTest
    public void Before(){
        System.out.println("测试之前");
    }



    @Test
    public void TestPos_Login(){
        String url = URL + "/spms/services/login";
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("cityCode", "330111");
        requestParam.put("meid", "e021a2e666f03bb5");
        requestParam.put("userPwd", "123456");
        requestParam.put("userName", "xxt");
        Map<String, Object> resultMap = HttpClientUtil.doPost(url, GsonUtil.objectToJson(requestParam));
        Assert.assertEquals(responseCode, 0, "The response code should be 200!");


    }


  @Test
    public void TestSpms_Login(){
        String url = URL + "/spms/checkuser.do ";
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("cityCode", "0");
        requestParam.put("account", "xxt");
        requestParam.put("password", "Kzz101369...");
        Map<String, Object> resultMap = HttpClientUtil.doPost(url, GsonUtil.objectToJson(requestParam));
   }



    @Test
    public void savePaking() {
        String url = URL + "/spms/park/save.do";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", "90");
        paramMap.put("parkName", "上线测试0001");
        paramMap.put("areaType", "01");
        paramMap.put("parkType", "00");
        paramMap.put("parkMode", "1");
        paramMap.put("isValid", "0");
        paramMap.put("longitude", "120.312512");
        paramMap.put("latitude", "120.312512");
        Map<String, Object> resultMap = HttpClientUtil.doPost(url, GsonUtil.objectToJson(paramMap));

    }


    @AfterTest
    public void After(){
        System.out.println("测试结束");
    }


}
