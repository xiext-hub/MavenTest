package com.chuangtai.testng;

import org.testng.annotations.Test;


//忽略测试
public class IgnoreTest {

    @Test
    public void Ignore1() {
        System.out.println("Ignore1 执行啦");
    }

    @Test(enabled = false)
    public void Ignore2() {
        System.out.println("Ignore2 执行啦");
    }

    @Test(enabled = true)
    public void Ignore3() {
        System.out.println("Ignore3 执行啦");
    }



}
