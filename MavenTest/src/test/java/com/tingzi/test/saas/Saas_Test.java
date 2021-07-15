package com.tingzi.test.saas;

import com.alibaba.fastjson.JSONObject;
import com.tingzi.util.HttpUtil;
import com.tingzi.util.RestClient;
import com.tingzi.util.SaasHttpUtil;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RestController;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @Author xiexiaoting
 * @Date 2021/7/9 10:47
 * @Version 1.0
 */

@RestController
public class Saas_Test extends SaasHttpUtil {

    private String url;
    private ResourceBundle bundle;
    private String json;
    String response = null;

    @BeforeTest
    public void BeforeTest() {
        //在测试之前，读取resource里面application.properties 里面的地址信息
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("saasTest.Host");
    }


    @Test
    public void saasTestLogin() throws Exception {
//        CloseableHttpClient httpClient = HttpClients.createDefault();  //创建一个httpclient对象,类似于浏览器客户端
//        HttpPost httpPost=new HttpPost(url);//创建一个请求对象
//        httpPost.addHeader("Content-type", "application/json");//添加请求头
        json = "{\"partnerNo\":\"8888888118\",\"appCode\":\"2021018888888252\",\"source\":\"OPERATOR_PORTAL_PC\",\"timestamp\":1626055314000,\"operation\":\"8818.gs.parkcloud.security.pwd.login\",\"bizReqData\":{\"mixAccount\":\"ra_admin\",\"password\":\"1c63129ae9db9c60c3e8aa94d3e00495\"}}";
//        StringEntity postingString = new StringEntity(json);
//        httpPost.setEntity(postingString);
//        HttpResponse httpResponse = httpClient.execute(httpPost);//执行post请求
//        int code=httpResponse.getStatusLine().getStatusCode();//从返回数据中获取状态码
//        String str= EntityUtils.toString(httpResponse.getEntity());//解析
//        Assert.assertEquals(code,200,"返回状态码不正确");//断言
//        Assert.assertTrue(str.contains("message"));
//        System.out.println(str);

        response = doPost(url, json);
        System.out.println(response);
    }

}
