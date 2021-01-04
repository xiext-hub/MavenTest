package com.chuangtai.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
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
    public void testGetWithCookies() throws IOException {
        String methodurl = bundle.getString("test.get.with.cookie"); //获取方法名
        String testurl = this.url + methodurl;//拼接url
        HttpGet get = new HttpGet(testurl);//get请求    DefaultHttpClient client=new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookie信息
        client.setCookieStore(this.store);
        response = client.execute(get);//执行get请求，并用response接收。
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");//格式转换
            System.out.println(statusCode + "~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }
}
