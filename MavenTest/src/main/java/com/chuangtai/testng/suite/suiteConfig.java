package com.chuangtai.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//套件测试
public class suiteConfig {
    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("BeforeSuite 执行辣");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite 执行辣");
    }

}
