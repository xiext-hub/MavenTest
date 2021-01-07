package com.chuangtai.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store; //用来存取cookies信息的变量
    private HttpResponse response;//用来存放响应内容的变量

    //这个测试用例是联合mock+application.properties配置文件来执行的，
    //所以执行测试之前需要执行 Java -jar ./moco-runner-0.11.0-standalone.jar http -p 8899 -c startupWithCookie.json

    @BeforeTest
    public void BeforeTest() {
        //在测试之前，读取resource里面application.properties 里面的地址信息
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String methodurl = bundle.getString("getCookies.url"); //从配置文件里面读取方法名
        String testUrl = this.url + methodurl;//拼接URL

        //get请求
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //执行get方法的
        response = client.execute(get);
        //获取响应内容，并转换格式
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);


        //获取cookie信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("访问/getcookies接口成功，cookie name = " + name + ", cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies") //依赖testGetCookies方法的cookie
    public void testPostMethod() throws IOException {
        //获取方法名
        String methoduri = bundle.getString("test.post.with.cookie");
        //拼接URL
        String testurl = this.url + methoduri;
        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "zhangsan");
        param.put("age", "18");
        //设置请求头信息
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象，来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转化成Json对象
        JSONObject resultjson = new JSONObject(result);
        //获取到结果的值
        String massage = (String) resultjson.get("massage");
        String status = (String) resultjson.get("status");

        //具体的判断返回结果的值  将实际结果与预期结果对比
        Assert.assertEquals("success", massage);
        Assert.assertEquals("1", status);
    }
}
