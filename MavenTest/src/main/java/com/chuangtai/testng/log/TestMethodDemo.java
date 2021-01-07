package com.chuangtai.testng.log;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

//此方法   执行res里面的testng.xml
//断言和log的使用
public class TestMethodDemo {
    @Test
    public  void test1(){
        Reporter.log("我是婷子噢");
        Assert.assertEquals(1,2);
    }

    @Test
    public  void test2(){

        Assert.assertEquals("test","test");
    }

    @Test
    public  void logDemo(){
        Reporter.log("这是我写的日志哦");
      //  throw new RuntimeException("这是我自己写的抛出的异常噢。");
    }
}
