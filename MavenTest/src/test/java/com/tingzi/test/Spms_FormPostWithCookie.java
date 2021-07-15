package com.tingzi.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tingzi.util.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

/**
 * @Author xiexiaoting
 * @Date 2021/1/14 15:01
 * @Version 1.0
 */
public class Spms_FormPostWithCookie extends RestClient {

    private String url;
    private ResourceBundle bundle;
    CloseableHttpResponse response = null;
    private  int parentId;


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
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("cityCode", "0");
        requestParam.put("account", "xiext");
        requestParam.put("password", "Xie816...");
        //执行获取响应结果
        response = postFormGetCookie(testurl, requestParam);
        //格式化响应内容
        String result = getResponseString(response);
        //获取响应码  断言
        checkStatusCode(response,200);
        System.out.println(response + "~~~~~~~~~~~~~~~~~~~");
        System.out.println(result + "~~~~~~~~~~~~~~~~~~~");
        Reporter.log("登录成功。");
        Reporter.log(result);
    }

    @Test(dependsOnMethods = "testGetCookie")
    public void testGetCtiyItems() throws IOException {
        String methodurl = bundle.getString("testGetCtiyItems.url");
        String testurl = this.url + methodurl; //拼接URL
        response = post(testurl);
        String result = getResponseString(response);
        System.out.println(result + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Reporter.log("获取区域接口成功。");
        Reporter.log(result);

        //取出接口返回里面的第二个 parentId
        List<JSONObject> jsonObjects = JSON.parseArray(result.toString(), JSONObject.class);
        parentId=jsonObjects.get(1).getInteger("parentId");
        System.out.println(parentId);

    }



    @Test(dependsOnMethods = "testGetCookie")
    public void testSavePack() throws IOException {
        String methodurl = bundle.getString("testSavePack.url");
        String testurl = this.url + methodurl; //拼接URL

        //设置参数  post-form表单提交
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("parentId", parentId);
        requestParam.put("parkName", "创泰停车点test8");
        requestParam.put("areaType", "01");
        requestParam.put("parkType", "00");
        requestParam.put("parkMode", "1");
        requestParam.put("isValid", "0");
        requestParam.put("address", "浙江省杭州市西湖区");
        requestParam.put("longitude", "120.316069");
        requestParam.put("latitude", "30.430717");
        //执行获取响应结果
        response = postFormSetCookie(testurl, requestParam);
        //格式化响应内容
        String result = getResponseString(response);
        System.out.println(response + "~~~~~~~~~~~~~~~~~~~");
        System.out.println(result + "~~~~~~~~~~~~~~~~~~~");
        Reporter.log("保存停车点成功。");
        Reporter.log(result);
    }
}
