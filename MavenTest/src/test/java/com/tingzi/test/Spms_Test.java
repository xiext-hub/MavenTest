package com.tingzi.test;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RestController;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/**
 * @Author xiexiaoting
 * @Date 2021/1/11 14:00
 * @Version 1.0
 */

@RestController
public class Spms_Test {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store; //用来存取cookies信息的变量


    @BeforeTest
    public void BeforeTest() {        //在测试之前，读取resource里面application.properties 里面的地址信息
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookie() throws IOException {
        String methodurl = bundle.getString("testGetCookie.url");
        String testurl = this.url + methodurl; //拼接URL
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
  //      HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //设置参数  post-form表单提交
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("cityCode", "0");
        requestParam.put("account", "xiext");
        requestParam.put("password", "Xie816...");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String temp : requestParam.keySet()) {
            list.add(new BasicNameValuePair(temp, requestParam.get(temp)));

        }
        //设置请求头信息
        post.setHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        //声明一个对象，来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = null;
        try {
          //  response = client.execute(proxy,post);
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        //获取cookie信息
        this.store = client.getCookieStore();
        List<org.apache.http.cookie.Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("访问/spms/checkuser.do接口成功，cookie name = " + name + ", cookie value = " + value);
            Reporter.log("访问/spms/checkuser.do接口成功,登录接口，存cookie");

        }
        Reporter.log(result);
    }


@Test(dependsOnMethods = "testGetCookie")
    public  void testGetCtiyItems() throws IOException {
    String methodurl = bundle.getString("testGetCtiyItems.url");
    String testurl = this.url + methodurl; //拼接URL
    System.out.println(testurl+"~~~~~~~~~~~~~~~~~~");
    DefaultHttpClient client = new DefaultHttpClient();
    //声明一个方法，这个方法就是post方法
    HttpPost post = new HttpPost(testurl);
   // HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理
    //声明一个对象，来进行响应结果的存储
    String result;
    //设置cookies信息
    client.setCookieStore(this.store);
    //执行post方法
   // HttpResponse response = client.execute(proxy,post);
    HttpResponse response = client.execute(post);
    //获取响应结果
    result = EntityUtils.toString(response.getEntity(),"utf-8");
    System.out.println(result+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    Reporter.log("获取区域接口成功。");
    Reporter.log(result);
    }

    @Test(dependsOnMethods = "testGetCookie")
    public void testSavePack() throws IOException {
        String methodurl = bundle.getString("testSavePack.url");
        String testurl = this.url + methodurl; //拼接URL
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
     //   HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //设置参数  post-form表单提交
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("parentId", "13");
        requestParam.put("parkName", "创泰停车点test2");
        requestParam.put("areaType", "01");
        requestParam.put("parkType", "00");
        requestParam.put("parkMode", "1");
        requestParam.put("isValid", "0");
        requestParam.put("address", "浙江省杭州市西湖区");
        requestParam.put("longitude", "120.316069");
        requestParam.put("latitude", "30.430717");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String temp : requestParam.keySet()) {
            list.add(new BasicNameValuePair(temp, requestParam.get(temp)));

        }
        //设置请求头信息
        post.setHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        //设置cookies信息
        client.setCookieStore(this.store);
        //声明一个对象，来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = null;
        try {
          //  response = client.execute(proxy,post);
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result+"~~~~~~~~~~~~~~~~~~~");
        Reporter.log("添加停车点成功。");
        Reporter.log(result);
        }


    @Test(dependsOnMethods = "testGetCookie")
    public void testSaveBowei() throws IOException {
        String methodurl = bundle.getString("testSavebowei.url");
        String testurl = this.url + methodurl; //拼接URL
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
       // HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //设置参数  post-form表单提交
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("regionId", "12");
        requestParam.put("streetId", "13");
        requestParam.put("parkId", "21142");
        requestParam.put("berthCode", "909091");
        requestParam.put("roadCode", "909091");
        requestParam.put("longitude", "120.315063");
        requestParam.put("latitude", "30.434329");
        requestParam.put("isValid", "0");
        requestParam.put("feeType", "0");
        requestParam.put("roadLocation", "00");
        requestParam.put("position", "01");
        requestParam.put("parkMode", "01");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String temp : requestParam.keySet()) {
            list.add(new BasicNameValuePair(temp, requestParam.get(temp)));

        }
        //设置请求头信息
        post.setHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        //设置cookies信息
        client.setCookieStore(this.store);
        //声明一个对象，来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = null;
        try {
           // response = client.execute(proxy,post);
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result+"~~~~~~~~~~~~~~~~~~~");
        Reporter.log("添加泊位成功。");
        Reporter.log(result);
    }



}