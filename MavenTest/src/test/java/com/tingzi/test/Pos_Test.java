package com.tingzi.test;
import com.tingzi.util.DateUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;
import static com.tingzi.util.DateUtil.getStringToday;
import static com.tingzi.util.DateUtil.parseDate;


/**
 * @Author xiexiaoting
 * @Date 2021/1/11 14:00
 * @Version 1.0
 */


@RestController
public class Pos_Test {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store; //用来存取cookies信息的变量

    @BeforeTest
    public void BeforeTest() {
        //在测试之前，读取resource里面application.properties 里面的地址信息
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void TestPos_Login() throws IOException  {
        //获取方法名地址
        String login_methodurl=bundle.getString("login.url");
        //拼接地址
        String testurl = this.url + login_methodurl;
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
      //  HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //  设置参数   ---json格式
        JSONObject param = new JSONObject();
        param.put("cityCode", "330111");
        param.put("meid", "e021a2e666f03bb5");
        param.put("userPwd", "123456");
        param.put("userName", "xxt");
        //设置请求头信息
        post.setHeader("content-type", "application/json; charset=UTF-8");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
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
        Reporter.log(result);
        Reporter.log("pos机登录成功。");

        System.out.println(result+"~~~~~~~~~~~~~~~");
    }


    @Test
    public void testqueryBerthByPark() throws IOException  {
        //获取方法名地址
        String login_methodurl=bundle.getString("testqueryBerthByPark.url");
        String loginkey=bundle.getString("loginKey");
        //拼接地址
        String testurl = this.url + login_methodurl;
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
        //  HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //  设置参数   ---json格式
        JSONObject param = new JSONObject();
        param.put("collectorId", "21181");
        param.put("loginKey", loginkey);
        param.put("parkId", "21142");
        //设置请求头信息
        post.setHeader("content-type", "application/json; charset=UTF-8");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
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
        Reporter.log(result);
        Reporter.log("pos机查询泊位信息成功。");
        System.out.println(result+"~~~~~~~~~~~~~~~");
    }




    @Test
    public void testArrive() throws IOException  {
        //获取方法名地址
        String login_methodurl=bundle.getString("testArrive.url");
        //拼接地址
        String testurl = this.url + login_methodurl;
        String loginkey=bundle.getString("loginKey");
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testurl);
        //  HttpHost proxy = new HttpHost("127.0.0.1",8888);  //设置fidder代理

        //  设置参数   ---json格式
        JSONObject param = new JSONObject();
        param.put("arriveTime", DateUtil.getStringTimestamp(parseDate(getStringToday())));//驶入时间
        param.put("berthId", "24502"); //泊位ID
        param.put("collectorId", "21181");//收费员ID
        // feeState:【00免费,01预付费,02未交费,04特殊车辆,05包月车辆,06部分缴费,07正常缴费,08预缴超额,09已绑卡,
        //# 10故障,11故障停车,12临时停车,13绑定车载标签,14超额退费,15绑定车主虚拟卡,16支付宝免密已绑定,25普通车辆】
        param.put("feeState", "07");
        param.put("loginKey", loginkey);
        param.put("plateColor", "0");//车牌颜色
        param.put("plateNo", "浙TEST33");
        //设置请求头信息
        post.setHeader("content-type", "application/json; charset=UTF-8");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
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
        Reporter.log(result);
        Reporter.log("pos机-车辆驶入成功。");
        System.out.println(result+"~~~~~~~~~~~~~~~");
    }


}
